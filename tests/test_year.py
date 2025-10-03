#!/usr/bin/env python3
"""
Tests for VIN year decoding heuristics (Python WMI fallback)
"""

from python.wmi_database import WMIDatabase


def make_vin(pos7_char: str, pos10_char: str) -> str:
    # 17-char VIN with safe letters (no I,O,Q), indices 0..16
    base = list("1ABCDEFGH1JKLMNR")
    base[6] = pos7_char  # position 7 (index 6)
    base[9] = pos10_char  # position 10 (index 9)
    return ''.join(base)


def test_year_decoding():
    cases = [
        (make_vin('1', '1'), 2001),  # pos7 digit, code '1' => 2001
        (make_vin('A', '1'), 2031),  # pos7 letter, code '1' => 2031
        (make_vin('2', '9'), 2009),  # pos7 digit, code '9' => 2009
        (make_vin('B', '9'), 2039),  # pos7 letter, code '9' => 2039
        (make_vin('3', 'A'), 1980),  # letter 'A', pos7 digit => 1980
        (make_vin('C', 'Y'), 2030),  # letter 'Y', pos7 letter => 2030
    ]

    passed = 0
    for vin, expected in cases:
        year = WMIDatabase.get_year(vin)
        if year == expected:
            passed += 1
        else:
            print(f"FAIL: {vin} expected {expected} got {year}")

    print(f"PythonYearDecodingTest: {passed}/{len(cases)} passed")
    assert passed == len(cases)


if __name__ == "__main__":
    test_year_decoding()

