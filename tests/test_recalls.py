#!/usr/bin/env python3
"""Unit tests for NHTSA recall integration."""

import json
import unittest
from typing import Dict
from unittest.mock import patch

from python.nhtsa_vin_decoder import NHTSAVinDecoder, VehicleData


class DummyResponse:
    """Simple context manager that mimics urllib responses."""

    def __init__(self, payload: Dict):
        self._payload = payload

    def read(self) -> bytes:
        return json.dumps(self._payload).encode("utf-8")

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        return False


def _sample_payload() -> Dict:
    return {
        "Count": 1,
        "Message": "Results returned successfully",
        "results": [
            {
                "Manufacturer": "Honda (American Honda Motor Co.)",
                "NHTSACampaignNumber": "19V182000",
                "parkIt": False,
                "parkOutSide": False,
                "overTheAirUpdate": False,
                "NHTSAActionNumber": "EA15001",
                "ReportReceivedDate": "06/03/2019",
                "Component": "AIR BAGS:FRONTAL:DRIVER SIDE:INFLATOR MODULE",
                "Summary": "Example summary",
                "Consequence": "Example consequence",
                "Remedy": "Example remedy",
                "Notes": "Example notes",
                "ModelYear": "2012",
                "Make": "ACURA",
                "Model": "RDX",
            }
        ],
    }


class RecallIntegrationTests(unittest.TestCase):
    """Validate recall helper methods."""

    def test_get_recalls_for_vehicle(self):
        decoder = NHTSAVinDecoder()

        with patch("python.nhtsa_vin_decoder.urllib.request.urlopen", return_value=DummyResponse(_sample_payload())):
            recalls = decoder.get_recalls_for_vehicle("Acura", "RDX", "2012")

        self.assertEqual(len(recalls), 1)
        record = recalls[0]
        self.assertEqual(record.manufacturer, "Honda (American Honda Motor Co.)")
        self.assertEqual(record.nhtsa_campaign_number, "19V182000")
        self.assertFalse(record.park_it)
        self.assertFalse(record.park_outside)
        self.assertFalse(record.over_the_air_update)

    def test_get_recalls_by_vin_enriches_vehicle(self):
        decoder = NHTSAVinDecoder()
        base_vehicle = VehicleData(
            vin="TESTVIN1234567890",
            make="Acura",
            manufacturer="Honda (American Honda Motor Co.)",
            model="RDX",
            year=2012,
        )

        with patch.object(NHTSAVinDecoder, "decode", return_value=base_vehicle):
            with patch("python.nhtsa_vin_decoder.urllib.request.urlopen", return_value=DummyResponse(_sample_payload())):
                enriched_vehicle, recalls = decoder.get_recalls_by_vin("TESTVIN1234567890")

        self.assertEqual(enriched_vehicle.vin, base_vehicle.vin)
        self.assertTrue(recalls)
        self.assertEqual(enriched_vehicle.recalls, recalls)
        self.assertEqual(enriched_vehicle.recalls[0].model_year, "2012")


if __name__ == "__main__":
    unittest.main()
