package io.github.vindecoder.offline;

import java.util.HashMap;
import java.util.Map;

/**
 * World Manufacturer Identifier (WMI) Database
 *
 * Comprehensive offline database of VIN manufacturer codes
 * Synchronized with Python implementation: 2,015+ manufacturer codes
 *
 * @author Wal33D
 */
public class WMIDatabase {

    private static final Map<String, String> WMI_MAP = new HashMap<>();
    private static final Map<Character, String> REGION_MAP = new HashMap<>();

    static {
        initializeRegions();
        initializeWMI();
    }

    private static void initializeRegions() {
        REGION_MAP.clear();
        // Grouped regional mapping by first character of VIN
        // North America
        REGION_MAP.put('1', "North America");
        REGION_MAP.put('2', "North America");
        REGION_MAP.put('3', "North America");
        REGION_MAP.put('4', "North America");
        REGION_MAP.put('5', "North America");
        // Oceania
        REGION_MAP.put('6', "Oceania");
        REGION_MAP.put('7', "Oceania");
        // South America
        REGION_MAP.put('8', "South America");
        REGION_MAP.put('9', "South America");
        // Africa (A-H)
        REGION_MAP.put('A', "Africa");
        REGION_MAP.put('B', "Africa");
        REGION_MAP.put('C', "Africa");
        REGION_MAP.put('D', "Africa");
        REGION_MAP.put('E', "Africa");
        REGION_MAP.put('F', "Africa");
        REGION_MAP.put('G', "Africa");
        REGION_MAP.put('H', "Africa");
        // Asia (J-R)
        REGION_MAP.put('J', "Asia");
        REGION_MAP.put('K', "Asia");
        REGION_MAP.put('L', "Asia");
        REGION_MAP.put('M', "Asia");
        REGION_MAP.put('N', "Asia");
        REGION_MAP.put('P', "Asia");
        REGION_MAP.put('R', "Asia");
        // Europe (S-Z)
        REGION_MAP.put('S', "Europe");
        REGION_MAP.put('T', "Europe");
        REGION_MAP.put('U', "Europe");
        REGION_MAP.put('V', "Europe");
        REGION_MAP.put('W', "Europe");
        REGION_MAP.put('X', "Europe");
        REGION_MAP.put('Y', "Europe");
        REGION_MAP.put('Z', "Europe");
    }

    private static void initializeWMI() {
        WMI_MAP.clear();
        // Total WMI codes: 2015

        // Acura (3 codes)
        WMI_MAP.put("2HN", "Acura");
        WMI_MAP.put("5J8", "Acura");
        WMI_MAP.put("JH4", "Acura");

        // Aixam (1 codes)
        WMI_MAP.put("VFU", "Aixam");

        // Alexander Dennis (2 codes)
        WMI_MAP.put("SFD", "Alexander Dennis");
        WMI_MAP.put("SFE", "Alexander Dennis");

        // Alfa Romeo (18 codes)
        WMI_MAP.put("ZAA", "Alfa Romeo");
        WMI_MAP.put("ZAE", "Alfa Romeo");
        WMI_MAP.put("ZAF", "Alfa Romeo");
        WMI_MAP.put("ZAG", "Alfa Romeo");
        WMI_MAP.put("ZAH", "Alfa Romeo");
        WMI_MAP.put("ZAJ", "Alfa Romeo");
        WMI_MAP.put("ZAK", "Alfa Romeo");
        WMI_MAP.put("ZAL", "Alfa Romeo");
        WMI_MAP.put("ZAP", "Alfa Romeo");
        WMI_MAP.put("ZAR", "Alfa Romeo");
        WMI_MAP.put("ZAS", "Alfa Romeo");
        WMI_MAP.put("ZAT", "Alfa Romeo");
        WMI_MAP.put("ZAU", "Alfa Romeo");
        WMI_MAP.put("ZAV", "Alfa Romeo");
        WMI_MAP.put("ZAW", "Alfa Romeo");
        WMI_MAP.put("ZAX", "Alfa Romeo");
        WMI_MAP.put("ZAY", "Alfa Romeo");
        WMI_MAP.put("ZAZ", "Alfa Romeo");

        // Alpine (1 codes)
        WMI_MAP.put("VFA", "Alpine");

        // Aprilia (3 codes)
        WMI_MAP.put("ZD4", "Aprilia");
        WMI_MAP.put("ZDS", "Aprilia");
        WMI_MAP.put("ZL0", "Aprilia");

        // Ashok Leyland (1 codes)
        WMI_MAP.put("MB1", "Ashok Leyland");

        // Aston Martin (2 codes)
        WMI_MAP.put("SCF", "Aston Martin");
        WMI_MAP.put("SCH", "Aston Martin");

        // Audi (35 codes)
        WMI_MAP.put("3AB", "Audi");
        WMI_MAP.put("6AB", "Audi");
        WMI_MAP.put("93U", "Audi");
        WMI_MAP.put("93V", "Audi");
        WMI_MAP.put("99A", "Audi");
        WMI_MAP.put("VUB", "Audi");
        WMI_MAP.put("WA0", "Audi");
        WMI_MAP.put("WA1", "Audi");
        WMI_MAP.put("WA8", "Audi");
        WMI_MAP.put("WAB", "Audi");
        WMI_MAP.put("WAC", "Audi");
        WMI_MAP.put("WAD", "Audi");
        WMI_MAP.put("WAE", "Audi");
        WMI_MAP.put("WAF", "Audi");
        WMI_MAP.put("WAG", "Audi");
        WMI_MAP.put("WAH", "Audi");
        WMI_MAP.put("WAJ", "Audi");
        WMI_MAP.put("WAK", "Audi");
        WMI_MAP.put("WAL", "Audi");
        WMI_MAP.put("WAM", "Audi");
        WMI_MAP.put("WAN", "Audi");
        WMI_MAP.put("WAP", "Audi");
        WMI_MAP.put("WAR", "Audi");
        WMI_MAP.put("WAS", "Audi");
        WMI_MAP.put("WAT", "Audi");
        WMI_MAP.put("WAU", "Audi");
        WMI_MAP.put("WAV", "Audi");
        WMI_MAP.put("WAW", "Audi");
        WMI_MAP.put("WAX", "Audi");
        WMI_MAP.put("WAY", "Audi");
        WMI_MAP.put("WAZ", "Audi");
        WMI_MAP.put("WRU", "Audi");
        WMI_MAP.put("WUA", "Audi");
        WMI_MAP.put("WUP", "Audi");
        WMI_MAP.put("WZA", "Audi");

        // Austin (1 codes)
        WMI_MAP.put("SAU", "Austin");

        // Autobianchi (1 codes)
        WMI_MAP.put("ZA8", "Autobianchi");

        // Autovia (1 codes)
        WMI_MAP.put("VG1", "Autovia");

        // AvtoVAZ (3 codes)
        WMI_MAP.put("X4A", "AvtoVAZ");
        WMI_MAP.put("X4X", "AvtoVAZ");
        WMI_MAP.put("XUE", "AvtoVAZ");

        // BAW (1 codes)
        WMI_MAP.put("LYX", "BAW");

        // BMW (42 codes)
        WMI_MAP.put("4US", "BMW");
        WMI_MAP.put("4UZ", "BMW");
        WMI_MAP.put("5UM", "BMW");
        WMI_MAP.put("5UX", "BMW");
        WMI_MAP.put("98M", "BMW");
        WMI_MAP.put("LBE", "BMW");
        WMI_MAP.put("LBM", "BMW");
        WMI_MAP.put("LBV", "BMW");
        WMI_MAP.put("MBU", "BMW");
        WMI_MAP.put("WB1", "BMW");
        WMI_MAP.put("WB2", "BMW");
        WMI_MAP.put("WB3", "BMW");
        WMI_MAP.put("WB4", "BMW");
        WMI_MAP.put("WB5", "BMW");
        WMI_MAP.put("WB6", "BMW");
        WMI_MAP.put("WB7", "BMW");
        WMI_MAP.put("WB8", "BMW");
        WMI_MAP.put("WB9", "BMW");
        WMI_MAP.put("WBA", "BMW");
        WMI_MAP.put("WBB", "BMW");
        WMI_MAP.put("WBC", "BMW");
        WMI_MAP.put("WBD", "BMW");
        WMI_MAP.put("WBE", "BMW");
        WMI_MAP.put("WBF", "BMW");
        WMI_MAP.put("WBG", "BMW");
        WMI_MAP.put("WBH", "BMW");
        WMI_MAP.put("WBJ", "BMW");
        WMI_MAP.put("WBK", "BMW");
        WMI_MAP.put("WBL", "BMW");
        WMI_MAP.put("WBM", "BMW");
        WMI_MAP.put("WBN", "BMW");
        WMI_MAP.put("WBP", "BMW");
        WMI_MAP.put("WBR", "BMW");
        WMI_MAP.put("WBS", "BMW");
        WMI_MAP.put("WBT", "BMW");
        WMI_MAP.put("WBU", "BMW");
        WMI_MAP.put("WBV", "BMW");
        WMI_MAP.put("WBW", "BMW");
        WMI_MAP.put("WBX", "BMW");
        WMI_MAP.put("WBY", "BMW");
        WMI_MAP.put("WBZ", "BMW");
        WMI_MAP.put("XUW", "BMW");

        // BYD (7 codes)
        WMI_MAP.put("LB1", "BYD");
        WMI_MAP.put("LB4", "BYD");
        WMI_MAP.put("LC0", "BYD");
        WMI_MAP.put("LC6", "BYD");
        WMI_MAP.put("LGX", "BYD");
        WMI_MAP.put("LYB", "BYD");
        WMI_MAP.put("LYM", "BYD");

        // Bajaj (2 codes)
        WMI_MAP.put("MD0", "Bajaj");
        WMI_MAP.put("MD2", "Bajaj");

        // Beijing (3 codes)
        WMI_MAP.put("LE4", "Beijing");
        WMI_MAP.put("LEN", "Beijing");
        WMI_MAP.put("LMB", "Beijing");

        // Beijing Hyundai (2 codes)
        WMI_MAP.put("LHB", "Beijing Hyundai");
        WMI_MAP.put("LHL", "Beijing Hyundai");

        // Bentley (2 codes)
        WMI_MAP.put("SCB", "Bentley");
        WMI_MAP.put("WCA", "Bentley");

        // Beta (1 codes)
        WMI_MAP.put("ZD3", "Beta");

        // Bimota (1 codes)
        WMI_MAP.put("ZEF", "Bimota");

        // Bogdan (1 codes)
        WMI_MAP.put("XU3", "Bogdan");

        // Borgward (1 codes)
        WMI_MAP.put("WYH", "Borgward");

        // Brilliance (3 codes)
        WMI_MAP.put("LYN", "Brilliance");
        WMI_MAP.put("LYS", "Brilliance");
        WMI_MAP.put("LYU", "Brilliance");

        // Bugatti (3 codes)
        WMI_MAP.put("VF9", "Bugatti");
        WMI_MAP.put("VFJ", "Bugatti");
        WMI_MAP.put("ZA9", "Bugatti");

        // Buick (5 codes)
        WMI_MAP.put("1G4", "Buick");
        WMI_MAP.put("2G4", "Buick");
        WMI_MAP.put("3G4", "Buick");
        WMI_MAP.put("3G5", "Buick");
        WMI_MAP.put("KL4", "Buick");

        // Cadillac (7 codes)
        WMI_MAP.put("1G6", "Cadillac");
        WMI_MAP.put("1GE", "Cadillac");
        WMI_MAP.put("1GY", "Cadillac");
        WMI_MAP.put("2G6", "Cadillac");
        WMI_MAP.put("3G6", "Cadillac");
        WMI_MAP.put("3GY", "Cadillac");
        WMI_MAP.put("KL6", "Cadillac");

        // Cagiva (2 codes)
        WMI_MAP.put("ZCG", "Cagiva");
        WMI_MAP.put("ZDX", "Cagiva");

        // Carrocerias Ayats (1 codes)
        WMI_MAP.put("VS9", "Carrocerias Ayats");

        // Carrus (1 codes)
        WMI_MAP.put("YS9", "Carrus");

        // Caterham (2 codes)
        WMI_MAP.put("SAZ", "Caterham");
        WMI_MAP.put("SXC", "Caterham");

        // Changan (22 codes)
        WMI_MAP.put("LWB", "Changan");
        WMI_MAP.put("LWC", "Changan");
        WMI_MAP.put("LWD", "Changan");
        WMI_MAP.put("LWE", "Changan");
        WMI_MAP.put("LWF", "Changan");
        WMI_MAP.put("LWG", "Changan");
        WMI_MAP.put("LWH", "Changan");
        WMI_MAP.put("LWJ", "Changan");
        WMI_MAP.put("LWK", "Changan");
        WMI_MAP.put("LWL", "Changan");
        WMI_MAP.put("LWM", "Changan");
        WMI_MAP.put("LWN", "Changan");
        WMI_MAP.put("LWP", "Changan");
        WMI_MAP.put("LWR", "Changan");
        WMI_MAP.put("LWS", "Changan");
        WMI_MAP.put("LWT", "Changan");
        WMI_MAP.put("LWU", "Changan");
        WMI_MAP.put("LWW", "Changan");
        WMI_MAP.put("LWX", "Changan");
        WMI_MAP.put("LWY", "Changan");
        WMI_MAP.put("LWZ", "Changan");
        WMI_MAP.put("LZZ", "Changan");

        // Changan PSA (1 codes)
        WMI_MAP.put("LPA", "Changan PSA");

        // Changan Suzuki (1 codes)
        WMI_MAP.put("LS5", "Changan Suzuki");

        // Chery (30 codes)
        WMI_MAP.put("98R", "Chery");
        WMI_MAP.put("LCA", "Chery");
        WMI_MAP.put("LCB", "Chery");
        WMI_MAP.put("LCC", "Chery");
        WMI_MAP.put("LCD", "Chery");
        WMI_MAP.put("LCE", "Chery");
        WMI_MAP.put("LP1", "Chery");
        WMI_MAP.put("LP8", "Chery");
        WMI_MAP.put("LPB", "Chery");
        WMI_MAP.put("LPC", "Chery");
        WMI_MAP.put("LPD", "Chery");
        WMI_MAP.put("LPF", "Chery");
        WMI_MAP.put("LPG", "Chery");
        WMI_MAP.put("LPH", "Chery");
        WMI_MAP.put("LPJ", "Chery");
        WMI_MAP.put("LPK", "Chery");
        WMI_MAP.put("LPL", "Chery");
        WMI_MAP.put("LPM", "Chery");
        WMI_MAP.put("LPN", "Chery");
        WMI_MAP.put("LPP", "Chery");
        WMI_MAP.put("LPR", "Chery");
        WMI_MAP.put("LPT", "Chery");
        WMI_MAP.put("LPU", "Chery");
        WMI_MAP.put("LPV", "Chery");
        WMI_MAP.put("LPW", "Chery");
        WMI_MAP.put("LPX", "Chery");
        WMI_MAP.put("LPY", "Chery");
        WMI_MAP.put("LPZ", "Chery");
        WMI_MAP.put("LVV", "Chery");
        WMI_MAP.put("XXV", "Chery");

        // Chevrolet (24 codes)
        WMI_MAP.put("1G1", "Chevrolet");
        WMI_MAP.put("1GB", "Chevrolet");
        WMI_MAP.put("1GC", "Chevrolet");
        WMI_MAP.put("1GD", "Chevrolet");
        WMI_MAP.put("1GG", "Chevrolet");
        WMI_MAP.put("1GN", "Chevrolet");
        WMI_MAP.put("2G1", "Chevrolet");
        WMI_MAP.put("2G8", "Chevrolet");
        WMI_MAP.put("2GN", "Chevrolet");
        WMI_MAP.put("3G1", "Chevrolet");
        WMI_MAP.put("3G8", "Chevrolet");
        WMI_MAP.put("3GC", "Chevrolet");
        WMI_MAP.put("3GE", "Chevrolet");
        WMI_MAP.put("3GN", "Chevrolet");
        WMI_MAP.put("6G3", "Chevrolet");
        WMI_MAP.put("9BG", "Chevrolet");
        WMI_MAP.put("KL1", "Chevrolet");
        WMI_MAP.put("KL5", "Chevrolet");
        WMI_MAP.put("KL7", "Chevrolet");
        WMI_MAP.put("KL8", "Chevrolet");
        WMI_MAP.put("LZG", "Chevrolet");
        WMI_MAP.put("LZP", "Chevrolet");
        WMI_MAP.put("XF9", "Chevrolet");
        WMI_MAP.put("XTU", "Chevrolet");

        // Chrysler (26 codes)
        WMI_MAP.put("1B4", "Chrysler");
        WMI_MAP.put("1C3", "Chrysler");
        WMI_MAP.put("1C4", "Chrysler");
        WMI_MAP.put("1C6", "Chrysler");
        WMI_MAP.put("1C8", "Chrysler");
        WMI_MAP.put("2A3", "Chrysler");
        WMI_MAP.put("2A4", "Chrysler");
        WMI_MAP.put("2A5", "Chrysler");
        WMI_MAP.put("2A8", "Chrysler");
        WMI_MAP.put("2C3", "Chrysler");
        WMI_MAP.put("2C4", "Chrysler");
        WMI_MAP.put("2C5", "Chrysler");
        WMI_MAP.put("2C6", "Chrysler");
        WMI_MAP.put("2C7", "Chrysler");
        WMI_MAP.put("2C8", "Chrysler");
        WMI_MAP.put("2CK", "Chrysler");
        WMI_MAP.put("2CM", "Chrysler");
        WMI_MAP.put("2CN", "Chrysler");
        WMI_MAP.put("2CP", "Chrysler");
        WMI_MAP.put("2CZ", "Chrysler");
        WMI_MAP.put("3C4", "Chrysler");
        WMI_MAP.put("3C6", "Chrysler");
        WMI_MAP.put("LCU", "Chrysler");
        WMI_MAP.put("LCV", "Chrysler");
        WMI_MAP.put("LCX", "Chrysler");
        WMI_MAP.put("ZC2", "Chrysler");

        // Citroen (1 codes)
        WMI_MAP.put("935", "Citroen");

        // Citroën (11 codes)
        WMI_MAP.put("VF7", "Citroën");
        WMI_MAP.put("VFB", "Citroën");
        WMI_MAP.put("VFC", "Citroën");
        WMI_MAP.put("VFY", "Citroën");
        WMI_MAP.put("VFZ", "Citroën");
        WMI_MAP.put("VGE", "Citroën");
        WMI_MAP.put("VLE", "Citroën");
        WMI_MAP.put("VLH", "Citroën");
        WMI_MAP.put("VSC", "Citroën");
        WMI_MAP.put("VTE", "Citroën");
        WMI_MAP.put("X89", "Citroën");

        // Dacia (2 codes)
        WMI_MAP.put("LZS", "Dacia");
        WMI_MAP.put("VFE", "Dacia");

        // Daelim (2 codes)
        WMI_MAP.put("VTY", "Daelim");
        WMI_MAP.put("VTZ", "Daelim");

        // Daewoo (14 codes)
        WMI_MAP.put("KLA", "Daewoo");
        WMI_MAP.put("KLC", "Daewoo");
        WMI_MAP.put("KLE", "Daewoo");
        WMI_MAP.put("KLF", "Daewoo");
        WMI_MAP.put("KLG", "Daewoo");
        WMI_MAP.put("KLH", "Daewoo");
        WMI_MAP.put("KLJ", "Daewoo");
        WMI_MAP.put("KLL", "Daewoo");
        WMI_MAP.put("KLM", "Daewoo");
        WMI_MAP.put("KLN", "Daewoo");
        WMI_MAP.put("KLT", "Daewoo");
        WMI_MAP.put("KLU", "Daewoo");
        WMI_MAP.put("KLY", "Daewoo");
        WMI_MAP.put("VTD", "Daewoo");

        // Daihatsu (7 codes)
        WMI_MAP.put("JD1", "Daihatsu");
        WMI_MAP.put("JD2", "Daihatsu");
        WMI_MAP.put("JD3", "Daihatsu");
        WMI_MAP.put("JD4", "Daihatsu");
        WMI_MAP.put("JDA", "Daihatsu");
        WMI_MAP.put("JDB", "Daihatsu");
        WMI_MAP.put("JDC", "Daihatsu");

        // Daimler (1 codes)
        WMI_MAP.put("SAC", "Daimler");

        // DeLorean (1 codes)
        WMI_MAP.put("SCE", "DeLorean");

        // Derbi (1 codes)
        WMI_MAP.put("VTR", "Derbi");

        // Dodge (28 codes)
        WMI_MAP.put("1B3", "Dodge");
        WMI_MAP.put("1B6", "Dodge");
        WMI_MAP.put("1B7", "Dodge");
        WMI_MAP.put("1D3", "Dodge");
        WMI_MAP.put("1D4", "Dodge");
        WMI_MAP.put("1D5", "Dodge");
        WMI_MAP.put("1D6", "Dodge");
        WMI_MAP.put("1D7", "Dodge");
        WMI_MAP.put("1D8", "Dodge");
        WMI_MAP.put("2B1", "Dodge");
        WMI_MAP.put("2B3", "Dodge");
        WMI_MAP.put("2B4", "Dodge");
        WMI_MAP.put("2B5", "Dodge");
        WMI_MAP.put("2B6", "Dodge");
        WMI_MAP.put("2B7", "Dodge");
        WMI_MAP.put("2B8", "Dodge");
        WMI_MAP.put("2D3", "Dodge");
        WMI_MAP.put("2D4", "Dodge");
        WMI_MAP.put("2D5", "Dodge");
        WMI_MAP.put("2D6", "Dodge");
        WMI_MAP.put("2D7", "Dodge");
        WMI_MAP.put("2D8", "Dodge");
        WMI_MAP.put("3D3", "Dodge");
        WMI_MAP.put("3D4", "Dodge");
        WMI_MAP.put("3D5", "Dodge");
        WMI_MAP.put("3D6", "Dodge");
        WMI_MAP.put("3D7", "Dodge");
        WMI_MAP.put("LZV", "Dodge");

        // Dongfeng (17 codes)
        WMI_MAP.put("LDC", "Dongfeng");
        WMI_MAP.put("LDD", "Dongfeng");
        WMI_MAP.put("LDE", "Dongfeng");
        WMI_MAP.put("LDF", "Dongfeng");
        WMI_MAP.put("LDG", "Dongfeng");
        WMI_MAP.put("LDH", "Dongfeng");
        WMI_MAP.put("LDJ", "Dongfeng");
        WMI_MAP.put("LDK", "Dongfeng");
        WMI_MAP.put("LDL", "Dongfeng");
        WMI_MAP.put("LGA", "Dongfeng");
        WMI_MAP.put("LGD", "Dongfeng");
        WMI_MAP.put("LGE", "Dongfeng");
        WMI_MAP.put("LGF", "Dongfeng");
        WMI_MAP.put("LGG", "Dongfeng");
        WMI_MAP.put("LGH", "Dongfeng");
        WMI_MAP.put("LJD", "Dongfeng");
        WMI_MAP.put("LZH", "Dongfeng");

        // Dongfeng Honda (1 codes)
        WMI_MAP.put("LHD", "Dongfeng Honda");

        // Ducati (3 codes)
        WMI_MAP.put("ZDG", "Ducati");
        WMI_MAP.put("ZDL", "Ducati");
        WMI_MAP.put("ZDM", "Ducati");

        // Eagle (1 codes)
        WMI_MAP.put("2E3", "Eagle");

        // FAW (28 codes)
        WMI_MAP.put("LF1", "FAW");
        WMI_MAP.put("LF3", "FAW");
        WMI_MAP.put("LF5", "FAW");
        WMI_MAP.put("LF7", "FAW");
        WMI_MAP.put("LFA", "FAW");
        WMI_MAP.put("LFB", "FAW");
        WMI_MAP.put("LFC", "FAW");
        WMI_MAP.put("LFD", "FAW");
        WMI_MAP.put("LFE", "FAW");
        WMI_MAP.put("LFF", "FAW");
        WMI_MAP.put("LFG", "FAW");
        WMI_MAP.put("LFH", "FAW");
        WMI_MAP.put("LFJ", "FAW");
        WMI_MAP.put("LFK", "FAW");
        WMI_MAP.put("LFL", "FAW");
        WMI_MAP.put("LFM", "FAW");
        WMI_MAP.put("LFN", "FAW");
        WMI_MAP.put("LFP", "FAW");
        WMI_MAP.put("LFT", "FAW");
        WMI_MAP.put("LFV", "FAW");
        WMI_MAP.put("LFW", "FAW");
        WMI_MAP.put("LFX", "FAW");
        WMI_MAP.put("LFY", "FAW");
        WMI_MAP.put("LFZ", "FAW");
        WMI_MAP.put("LH1", "FAW");
        WMI_MAP.put("LYH", "FAW");
        WMI_MAP.put("LYR", "FAW");
        WMI_MAP.put("LYT", "FAW");

        // FAW Toyota (1 codes)
        WMI_MAP.put("LVH", "FAW Toyota");

        // Ferrari (2 codes)
        WMI_MAP.put("ZDF", "Ferrari");
        WMI_MAP.put("ZFF", "Ferrari");

        // Fiat (49 codes)
        WMI_MAP.put("93W", "Fiat");
        WMI_MAP.put("9BD", "Fiat");
        WMI_MAP.put("MCA", "Fiat");
        WMI_MAP.put("SUF", "Fiat");
        WMI_MAP.put("ZBA", "Fiat");
        WMI_MAP.put("ZBB", "Fiat");
        WMI_MAP.put("ZBC", "Fiat");
        WMI_MAP.put("ZBD", "Fiat");
        WMI_MAP.put("ZBE", "Fiat");
        WMI_MAP.put("ZBF", "Fiat");
        WMI_MAP.put("ZBG", "Fiat");
        WMI_MAP.put("ZBH", "Fiat");
        WMI_MAP.put("ZBJ", "Fiat");
        WMI_MAP.put("ZBK", "Fiat");
        WMI_MAP.put("ZBL", "Fiat");
        WMI_MAP.put("ZBM", "Fiat");
        WMI_MAP.put("ZBN", "Fiat");
        WMI_MAP.put("ZBP", "Fiat");
        WMI_MAP.put("ZBR", "Fiat");
        WMI_MAP.put("ZBS", "Fiat");
        WMI_MAP.put("ZBT", "Fiat");
        WMI_MAP.put("ZBU", "Fiat");
        WMI_MAP.put("ZBV", "Fiat");
        WMI_MAP.put("ZBW", "Fiat");
        WMI_MAP.put("ZBX", "Fiat");
        WMI_MAP.put("ZBY", "Fiat");
        WMI_MAP.put("ZBZ", "Fiat");
        WMI_MAP.put("ZFA", "Fiat");
        WMI_MAP.put("ZFB", "Fiat");
        WMI_MAP.put("ZFC", "Fiat");
        WMI_MAP.put("ZFD", "Fiat");
        WMI_MAP.put("ZFE", "Fiat");
        WMI_MAP.put("ZFG", "Fiat");
        WMI_MAP.put("ZFH", "Fiat");
        WMI_MAP.put("ZFJ", "Fiat");
        WMI_MAP.put("ZFK", "Fiat");
        WMI_MAP.put("ZFL", "Fiat");
        WMI_MAP.put("ZFM", "Fiat");
        WMI_MAP.put("ZFN", "Fiat");
        WMI_MAP.put("ZFP", "Fiat");
        WMI_MAP.put("ZFR", "Fiat");
        WMI_MAP.put("ZFS", "Fiat");
        WMI_MAP.put("ZFT", "Fiat");
        WMI_MAP.put("ZFU", "Fiat");
        WMI_MAP.put("ZFV", "Fiat");
        WMI_MAP.put("ZFW", "Fiat");
        WMI_MAP.put("ZFX", "Fiat");
        WMI_MAP.put("ZFY", "Fiat");
        WMI_MAP.put("ZFZ", "Fiat");

        // Force (1 codes)
        WMI_MAP.put("MCF", "Force");

        // Ford (87 codes)
        WMI_MAP.put("1F1", "Ford");
        WMI_MAP.put("1F2", "Ford");
        WMI_MAP.put("1F3", "Ford");
        WMI_MAP.put("1F4", "Ford");
        WMI_MAP.put("1F5", "Ford");
        WMI_MAP.put("1F6", "Ford");
        WMI_MAP.put("1F7", "Ford");
        WMI_MAP.put("1F8", "Ford");
        WMI_MAP.put("1F9", "Ford");
        WMI_MAP.put("1FA", "Ford");
        WMI_MAP.put("1FB", "Ford");
        WMI_MAP.put("1FC", "Ford");
        WMI_MAP.put("1FD", "Ford");
        WMI_MAP.put("1FM", "Ford");
        WMI_MAP.put("1FT", "Ford");
        WMI_MAP.put("1FU", "Ford");
        WMI_MAP.put("1FV", "Ford");
        WMI_MAP.put("1ZV", "Ford");
        WMI_MAP.put("2FA", "Ford");
        WMI_MAP.put("2FB", "Ford");
        WMI_MAP.put("2FC", "Ford");
        WMI_MAP.put("2FD", "Ford");
        WMI_MAP.put("2FM", "Ford");
        WMI_MAP.put("2FT", "Ford");
        WMI_MAP.put("2FU", "Ford");
        WMI_MAP.put("2FV", "Ford");
        WMI_MAP.put("2FW", "Ford");
        WMI_MAP.put("2FZ", "Ford");
        WMI_MAP.put("3FA", "Ford");
        WMI_MAP.put("3FB", "Ford");
        WMI_MAP.put("3FC", "Ford");
        WMI_MAP.put("3FD", "Ford");
        WMI_MAP.put("3FE", "Ford");
        WMI_MAP.put("3FR", "Ford");
        WMI_MAP.put("3FT", "Ford");
        WMI_MAP.put("6F1", "Ford");
        WMI_MAP.put("6FP", "Ford");
        WMI_MAP.put("9BF", "Ford");
        WMI_MAP.put("LT0", "Ford");
        WMI_MAP.put("LT8", "Ford");
        WMI_MAP.put("LT9", "Ford");
        WMI_MAP.put("LTA", "Ford");
        WMI_MAP.put("LTB", "Ford");
        WMI_MAP.put("LTC", "Ford");
        WMI_MAP.put("LTD", "Ford");
        WMI_MAP.put("LTE", "Ford");
        WMI_MAP.put("LTF", "Ford");
        WMI_MAP.put("LTG", "Ford");
        WMI_MAP.put("LTH", "Ford");
        WMI_MAP.put("LTJ", "Ford");
        WMI_MAP.put("LTK", "Ford");
        WMI_MAP.put("LTL", "Ford");
        WMI_MAP.put("LTM", "Ford");
        WMI_MAP.put("LTN", "Ford");
        WMI_MAP.put("LTP", "Ford");
        WMI_MAP.put("LTR", "Ford");
        WMI_MAP.put("LTS", "Ford");
        WMI_MAP.put("LTT", "Ford");
        WMI_MAP.put("LTU", "Ford");
        WMI_MAP.put("LTV", "Ford");
        WMI_MAP.put("LTW", "Ford");
        WMI_MAP.put("LTX", "Ford");
        WMI_MAP.put("LTY", "Ford");
        WMI_MAP.put("LTZ", "Ford");
        WMI_MAP.put("LVS", "Ford");
        WMI_MAP.put("MAJ", "Ford");
        WMI_MAP.put("SDF", "Ford");
        WMI_MAP.put("SFA", "Ford");
        WMI_MAP.put("SFB", "Ford");
        WMI_MAP.put("SFC", "Ford");
        WMI_MAP.put("SFF", "Ford");
        WMI_MAP.put("SFG", "Ford");
        WMI_MAP.put("SFH", "Ford");
        WMI_MAP.put("SFJ", "Ford");
        WMI_MAP.put("SFK", "Ford");
        WMI_MAP.put("SFL", "Ford");
        WMI_MAP.put("SFN", "Ford");
        WMI_MAP.put("SFP", "Ford");
        WMI_MAP.put("SFR", "Ford");
        WMI_MAP.put("SFS", "Ford");
        WMI_MAP.put("VS6", "Ford");
        WMI_MAP.put("VTW", "Ford");
        WMI_MAP.put("WF0", "Ford");
        WMI_MAP.put("WF1", "Ford");
        WMI_MAP.put("XTF", "Ford");
        WMI_MAP.put("XUX", "Ford");
        WMI_MAP.put("XW7", "Ford");

        // Freight Rover (1 codes)
        WMI_MAP.put("SAS", "Freight Rover");

        // Freightliner (1 codes)
        WMI_MAP.put("2WK", "Freightliner");

        // GAC (13 codes)
        WMI_MAP.put("LGJ", "GAC");
        WMI_MAP.put("LGK", "GAC");
        WMI_MAP.put("LGL", "GAC");
        WMI_MAP.put("LGN", "GAC");
        WMI_MAP.put("LGP", "GAC");
        WMI_MAP.put("LGR", "GAC");
        WMI_MAP.put("LGS", "GAC");
        WMI_MAP.put("LGT", "GAC");
        WMI_MAP.put("LGU", "GAC");
        WMI_MAP.put("LGY", "GAC");
        WMI_MAP.put("LGZ", "GAC");
        WMI_MAP.put("LMG", "GAC");
        WMI_MAP.put("LWV", "GAC");

        // GAC Honda (1 codes)
        WMI_MAP.put("LHG", "GAC Honda");

        // GAC Toyota (1 codes)
        WMI_MAP.put("LVG", "GAC Toyota");

        // GM (9 codes)
        WMI_MAP.put("LAG", "GM");
        WMI_MAP.put("LAL", "GM");
        WMI_MAP.put("LAN", "GM");
        WMI_MAP.put("LAR", "GM");
        WMI_MAP.put("MA6", "GM");
        WMI_MAP.put("MCB", "GM");
        WMI_MAP.put("X9L", "GM");
        WMI_MAP.put("XUF", "GM");
        WMI_MAP.put("XUU", "GM");

        // GMC (12 codes)
        WMI_MAP.put("1G5", "GMC");
        WMI_MAP.put("1GJ", "GMC");
        WMI_MAP.put("1GK", "GMC");
        WMI_MAP.put("1GT", "GMC");
        WMI_MAP.put("2G0", "GMC");
        WMI_MAP.put("2G5", "GMC");
        WMI_MAP.put("2GK", "GMC");
        WMI_MAP.put("2GT", "GMC");
        WMI_MAP.put("3G7", "GMC");
        WMI_MAP.put("3GD", "GMC");
        WMI_MAP.put("3GK", "GMC");
        WMI_MAP.put("3GT", "GMC");

        // Geely (7 codes)
        WMI_MAP.put("LB2", "Geely");
        WMI_MAP.put("LB3", "Geely");
        WMI_MAP.put("LT2", "Geely");
        WMI_MAP.put("LT5", "Geely");
        WMI_MAP.put("LT7", "Geely");
        WMI_MAP.put("LYP", "Geely");
        WMI_MAP.put("LYV", "Geely");

        // General Motors (2 codes)
        WMI_MAP.put("SDG", "General Motors");
        WMI_MAP.put("VSD", "General Motors");

        // General Motors Europe (1 codes)
        WMI_MAP.put("SED", "General Motors Europe");

        // Genesis (1 codes)
        WMI_MAP.put("KMY", "Genesis");

        // Geo (3 codes)
        WMI_MAP.put("1G9", "Geo");
        WMI_MAP.put("2G9", "Geo");
        WMI_MAP.put("JGC", "Geo");

        // Gilera (2 codes)
        WMI_MAP.put("ZGH", "Gilera");
        WMI_MAP.put("ZGR", "Gilera");

        // Ginetta (1 codes)
        WMI_MAP.put("SSG", "Ginetta");

        // Great Wall (4 codes)
        WMI_MAP.put("LB5", "Great Wall");
        WMI_MAP.put("LB6", "Great Wall");
        WMI_MAP.put("LGW", "Great Wall");
        WMI_MAP.put("LZL", "Great Wall");

        // Gruau (1 codes)
        WMI_MAP.put("VFT", "Gruau");

        // Guangzhou (1 codes)
        WMI_MAP.put("LYG", "Guangzhou");

        // Haval (1 codes)
        WMI_MAP.put("LZK", "Haval");

        // Hero (22 codes)
        WMI_MAP.put("MD9", "Hero");
        WMI_MAP.put("MDA", "Hero");
        WMI_MAP.put("MDB", "Hero");
        WMI_MAP.put("MDC", "Hero");
        WMI_MAP.put("MDD", "Hero");
        WMI_MAP.put("MDE", "Hero");
        WMI_MAP.put("MDF", "Hero");
        WMI_MAP.put("MDG", "Hero");
        WMI_MAP.put("MDH", "Hero");
        WMI_MAP.put("MDK", "Hero");
        WMI_MAP.put("MDL", "Hero");
        WMI_MAP.put("MDM", "Hero");
        WMI_MAP.put("MDN", "Hero");
        WMI_MAP.put("MDP", "Hero");
        WMI_MAP.put("MDR", "Hero");
        WMI_MAP.put("MDS", "Hero");
        WMI_MAP.put("MDT", "Hero");
        WMI_MAP.put("MDU", "Hero");
        WMI_MAP.put("MDV", "Hero");
        WMI_MAP.put("MDW", "Hero");
        WMI_MAP.put("MDX", "Hero");
        WMI_MAP.put("MDY", "Hero");

        // Holden (3 codes)
        WMI_MAP.put("6G1", "Holden");
        WMI_MAP.put("6H8", "Holden");
        WMI_MAP.put("KL3", "Holden");

        // Honda (87 codes)
        WMI_MAP.put("1H4", "Honda");
        WMI_MAP.put("1HF", "Honda");
        WMI_MAP.put("1HG", "Honda");
        WMI_MAP.put("1HH", "Honda");
        WMI_MAP.put("2HG", "Honda");
        WMI_MAP.put("2HJ", "Honda");
        WMI_MAP.put("2HK", "Honda");
        WMI_MAP.put("3HG", "Honda");
        WMI_MAP.put("3HM", "Honda");
        WMI_MAP.put("4S6", "Honda");
        WMI_MAP.put("4S7", "Honda");
        WMI_MAP.put("5FN", "Honda");
        WMI_MAP.put("5FP", "Honda");
        WMI_MAP.put("5FR", "Honda");
        WMI_MAP.put("5J6", "Honda");
        WMI_MAP.put("5YH", "Honda");
        WMI_MAP.put("5YP", "Honda");
        WMI_MAP.put("5YR", "Honda");
        WMI_MAP.put("93H", "Honda");
        WMI_MAP.put("JH2", "Honda");
        WMI_MAP.put("JH3", "Honda");
        WMI_MAP.put("JH5", "Honda");
        WMI_MAP.put("JHA", "Honda");
        WMI_MAP.put("JHB", "Honda");
        WMI_MAP.put("JHD", "Honda");
        WMI_MAP.put("JHE", "Honda");
        WMI_MAP.put("JHF", "Honda");
        WMI_MAP.put("JHG", "Honda");
        WMI_MAP.put("JHL", "Honda");
        WMI_MAP.put("JHM", "Honda");
        WMI_MAP.put("LUC", "Honda");
        WMI_MAP.put("LUD", "Honda");
        WMI_MAP.put("LUE", "Honda");
        WMI_MAP.put("LUF", "Honda");
        WMI_MAP.put("LUG", "Honda");
        WMI_MAP.put("LUH", "Honda");
        WMI_MAP.put("LUJ", "Honda");
        WMI_MAP.put("LUK", "Honda");
        WMI_MAP.put("LUL", "Honda");
        WMI_MAP.put("LUM", "Honda");
        WMI_MAP.put("LUN", "Honda");
        WMI_MAP.put("LUP", "Honda");
        WMI_MAP.put("LUR", "Honda");
        WMI_MAP.put("LUS", "Honda");
        WMI_MAP.put("LUT", "Honda");
        WMI_MAP.put("LUU", "Honda");
        WMI_MAP.put("LUV", "Honda");
        WMI_MAP.put("LUW", "Honda");
        WMI_MAP.put("LUX", "Honda");
        WMI_MAP.put("LUY", "Honda");
        WMI_MAP.put("LUZ", "Honda");
        WMI_MAP.put("MAK", "Honda");
        WMI_MAP.put("MCR", "Honda");
        WMI_MAP.put("SAH", "Honda");
        WMI_MAP.put("SDH", "Honda");
        WMI_MAP.put("SH0", "Honda");
        WMI_MAP.put("SHA", "Honda");
        WMI_MAP.put("SHB", "Honda");
        WMI_MAP.put("SHC", "Honda");
        WMI_MAP.put("SHD", "Honda");
        WMI_MAP.put("SHE", "Honda");
        WMI_MAP.put("SHF", "Honda");
        WMI_MAP.put("SHG", "Honda");
        WMI_MAP.put("SHH", "Honda");
        WMI_MAP.put("SHJ", "Honda");
        WMI_MAP.put("SHK", "Honda");
        WMI_MAP.put("SHL", "Honda");
        WMI_MAP.put("SHM", "Honda");
        WMI_MAP.put("SHN", "Honda");
        WMI_MAP.put("SHP", "Honda");
        WMI_MAP.put("SHR", "Honda");
        WMI_MAP.put("SHS", "Honda");
        WMI_MAP.put("SHT", "Honda");
        WMI_MAP.put("SHU", "Honda");
        WMI_MAP.put("SHV", "Honda");
        WMI_MAP.put("SHW", "Honda");
        WMI_MAP.put("SHX", "Honda");
        WMI_MAP.put("SHY", "Honda");
        WMI_MAP.put("SHZ", "Honda");
        WMI_MAP.put("SSH", "Honda");
        WMI_MAP.put("VTK", "Honda");
        WMI_MAP.put("VTM", "Honda");
        WMI_MAP.put("VTN", "Honda");
        WMI_MAP.put("YH2", "Honda");
        WMI_MAP.put("ZCH", "Honda");
        WMI_MAP.put("ZDC", "Honda");
        WMI_MAP.put("ZKH", "Honda");

        // Huanghai (1 codes)
        WMI_MAP.put("LL6", "Huanghai");

        // Husqvarna (25 codes)
        WMI_MAP.put("YUA", "Husqvarna");
        WMI_MAP.put("YUB", "Husqvarna");
        WMI_MAP.put("YUC", "Husqvarna");
        WMI_MAP.put("YUD", "Husqvarna");
        WMI_MAP.put("YUE", "Husqvarna");
        WMI_MAP.put("YUF", "Husqvarna");
        WMI_MAP.put("YUG", "Husqvarna");
        WMI_MAP.put("YUH", "Husqvarna");
        WMI_MAP.put("YUJ", "Husqvarna");
        WMI_MAP.put("YUK", "Husqvarna");
        WMI_MAP.put("YUL", "Husqvarna");
        WMI_MAP.put("YUM", "Husqvarna");
        WMI_MAP.put("YUN", "Husqvarna");
        WMI_MAP.put("YUP", "Husqvarna");
        WMI_MAP.put("YUR", "Husqvarna");
        WMI_MAP.put("YUS", "Husqvarna");
        WMI_MAP.put("YUT", "Husqvarna");
        WMI_MAP.put("YUU", "Husqvarna");
        WMI_MAP.put("YUV", "Husqvarna");
        WMI_MAP.put("YUW", "Husqvarna");
        WMI_MAP.put("YUX", "Husqvarna");
        WMI_MAP.put("YUY", "Husqvarna");
        WMI_MAP.put("YUZ", "Husqvarna");
        WMI_MAP.put("ZDH", "Husqvarna");
        WMI_MAP.put("ZEH", "Husqvarna");

        // Hyosung (1 codes)
        WMI_MAP.put("ZH2", "Hyosung");

        // Hyundai (45 codes)
        WMI_MAP.put("2HM", "Hyundai");
        WMI_MAP.put("5NM", "Hyundai");
        WMI_MAP.put("5NP", "Hyundai");
        WMI_MAP.put("5X3", "Hyundai");
        WMI_MAP.put("5X4", "Hyundai");
        WMI_MAP.put("5X5", "Hyundai");
        WMI_MAP.put("5X7", "Hyundai");
        WMI_MAP.put("9BH", "Hyundai");
        WMI_MAP.put("KM1", "Hyundai");
        WMI_MAP.put("KM2", "Hyundai");
        WMI_MAP.put("KM3", "Hyundai");
        WMI_MAP.put("KM4", "Hyundai");
        WMI_MAP.put("KM5", "Hyundai");
        WMI_MAP.put("KM6", "Hyundai");
        WMI_MAP.put("KM7", "Hyundai");
        WMI_MAP.put("KM8", "Hyundai");
        WMI_MAP.put("KM9", "Hyundai");
        WMI_MAP.put("KMC", "Hyundai");
        WMI_MAP.put("KMD", "Hyundai");
        WMI_MAP.put("KME", "Hyundai");
        WMI_MAP.put("KMF", "Hyundai");
        WMI_MAP.put("KMG", "Hyundai");
        WMI_MAP.put("KMH", "Hyundai");
        WMI_MAP.put("KMJ", "Hyundai");
        WMI_MAP.put("KMK", "Hyundai");
        WMI_MAP.put("KML", "Hyundai");
        WMI_MAP.put("KMN", "Hyundai");
        WMI_MAP.put("KMP", "Hyundai");
        WMI_MAP.put("KMR", "Hyundai");
        WMI_MAP.put("KMS", "Hyundai");
        WMI_MAP.put("KMT", "Hyundai");
        WMI_MAP.put("KMU", "Hyundai");
        WMI_MAP.put("KMV", "Hyundai");
        WMI_MAP.put("KMW", "Hyundai");
        WMI_MAP.put("KMX", "Hyundai");
        WMI_MAP.put("KMZ", "Hyundai");
        WMI_MAP.put("LYY", "Hyundai");
        WMI_MAP.put("LZX", "Hyundai");
        WMI_MAP.put("MAL", "Hyundai");
        WMI_MAP.put("MBH", "Hyundai");
        WMI_MAP.put("VTH", "Hyundai");
        WMI_MAP.put("XE0", "Hyundai");
        WMI_MAP.put("XFA", "Hyundai");
        WMI_MAP.put("XUB", "Hyundai");
        WMI_MAP.put("XWK", "Hyundai");

        // IBC Vehicles (1 codes)
        WMI_MAP.put("ZCA", "IBC Vehicles");

        // Infiniti (5 codes)
        WMI_MAP.put("1NX", "Infiniti");
        WMI_MAP.put("5N3", "Infiniti");
        WMI_MAP.put("JNK", "Infiniti");
        WMI_MAP.put("JNR", "Infiniti");
        WMI_MAP.put("JNX", "Infiniti");

        // Innocenti (18 codes)
        WMI_MAP.put("ZA2", "Innocenti");
        WMI_MAP.put("ZCJ", "Innocenti");
        WMI_MAP.put("ZCK", "Innocenti");
        WMI_MAP.put("ZCL", "Innocenti");
        WMI_MAP.put("ZCM", "Innocenti");
        WMI_MAP.put("ZCN", "Innocenti");
        WMI_MAP.put("ZCP", "Innocenti");
        WMI_MAP.put("ZCR", "Innocenti");
        WMI_MAP.put("ZCS", "Innocenti");
        WMI_MAP.put("ZCT", "Innocenti");
        WMI_MAP.put("ZCU", "Innocenti");
        WMI_MAP.put("ZCV", "Innocenti");
        WMI_MAP.put("ZCW", "Innocenti");
        WMI_MAP.put("ZCX", "Innocenti");
        WMI_MAP.put("ZCY", "Innocenti");
        WMI_MAP.put("ZCZ", "Innocenti");
        WMI_MAP.put("ZJ2", "Innocenti");
        WMI_MAP.put("ZJN", "Innocenti");

        // Isuzu (13 codes)
        WMI_MAP.put("4S1", "Isuzu");
        WMI_MAP.put("4S2", "Isuzu");
        WMI_MAP.put("JAA", "Isuzu");
        WMI_MAP.put("JAB", "Isuzu");
        WMI_MAP.put("JAC", "Isuzu");
        WMI_MAP.put("JAE", "Isuzu");
        WMI_MAP.put("JAL", "Isuzu");
        WMI_MAP.put("JAN", "Isuzu");
        WMI_MAP.put("JAS", "Isuzu");
        WMI_MAP.put("LZD", "Isuzu");
        WMI_MAP.put("LZE", "Isuzu");
        WMI_MAP.put("MCL", "Isuzu");
        WMI_MAP.put("XK9", "Isuzu");

        // Italjet (1 codes)
        WMI_MAP.put("ZET", "Italjet");

        // Iveco (22 codes)
        WMI_MAP.put("SFT", "Iveco");
        WMI_MAP.put("VNE", "Iveco");
        WMI_MAP.put("ZCF", "Iveco");
        WMI_MAP.put("ZGA", "Iveco");
        WMI_MAP.put("ZGB", "Iveco");
        WMI_MAP.put("ZGC", "Iveco");
        WMI_MAP.put("ZGD", "Iveco");
        WMI_MAP.put("ZGE", "Iveco");
        WMI_MAP.put("ZGF", "Iveco");
        WMI_MAP.put("ZGJ", "Iveco");
        WMI_MAP.put("ZGK", "Iveco");
        WMI_MAP.put("ZGL", "Iveco");
        WMI_MAP.put("ZGM", "Iveco");
        WMI_MAP.put("ZGN", "Iveco");
        WMI_MAP.put("ZGP", "Iveco");
        WMI_MAP.put("ZGS", "Iveco");
        WMI_MAP.put("ZGT", "Iveco");
        WMI_MAP.put("ZGV", "Iveco");
        WMI_MAP.put("ZGW", "Iveco");
        WMI_MAP.put("ZGX", "Iveco");
        WMI_MAP.put("ZGY", "Iveco");
        WMI_MAP.put("ZGZ", "Iveco");

        // JAC (29 codes)
        WMI_MAP.put("LJ1", "JAC");
        WMI_MAP.put("LJ2", "JAC");
        WMI_MAP.put("LJ3", "JAC");
        WMI_MAP.put("LJ4", "JAC");
        WMI_MAP.put("LJ5", "JAC");
        WMI_MAP.put("LJ8", "JAC");
        WMI_MAP.put("LJ9", "JAC");
        WMI_MAP.put("LJA", "JAC");
        WMI_MAP.put("LJB", "JAC");
        WMI_MAP.put("LJC", "JAC");
        WMI_MAP.put("LJE", "JAC");
        WMI_MAP.put("LJF", "JAC");
        WMI_MAP.put("LJG", "JAC");
        WMI_MAP.put("LJH", "JAC");
        WMI_MAP.put("LJJ", "JAC");
        WMI_MAP.put("LJK", "JAC");
        WMI_MAP.put("LJL", "JAC");
        WMI_MAP.put("LJM", "JAC");
        WMI_MAP.put("LJN", "JAC");
        WMI_MAP.put("LJP", "JAC");
        WMI_MAP.put("LJR", "JAC");
        WMI_MAP.put("LJS", "JAC");
        WMI_MAP.put("LJT", "JAC");
        WMI_MAP.put("LJU", "JAC");
        WMI_MAP.put("LJV", "JAC");
        WMI_MAP.put("LJW", "JAC");
        WMI_MAP.put("LJX", "JAC");
        WMI_MAP.put("LJY", "JAC");
        WMI_MAP.put("LJZ", "JAC");

        // Jaguar (9 codes)
        WMI_MAP.put("99J", "Jaguar");
        WMI_MAP.put("SAD", "Jaguar");
        WMI_MAP.put("SAF", "Jaguar");
        WMI_MAP.put("SAG", "Jaguar");
        WMI_MAP.put("SAJ", "Jaguar");
        WMI_MAP.put("SAK", "Jaguar");
        WMI_MAP.put("SAX", "Jaguar");
        WMI_MAP.put("SDK", "Jaguar");
        WMI_MAP.put("SJR", "Jaguar");

        // Jeep (6 codes)
        WMI_MAP.put("1J4", "Jeep");
        WMI_MAP.put("1J7", "Jeep");
        WMI_MAP.put("1J8", "Jeep");
        WMI_MAP.put("2J4", "Jeep");
        WMI_MAP.put("4J4", "Jeep");
        WMI_MAP.put("4J8", "Jeep");

        // KAMAZ (4 codes)
        WMI_MAP.put("XET", "KAMAZ");
        WMI_MAP.put("XFC", "KAMAZ");
        WMI_MAP.put("XN1", "KAMAZ");
        WMI_MAP.put("XTC", "KAMAZ");

        // KTM (4 codes)
        WMI_MAP.put("ZEA", "KTM");
        WMI_MAP.put("ZEB", "KTM");
        WMI_MAP.put("ZEC", "KTM");
        WMI_MAP.put("ZED", "KTM");

        // Kavz (1 codes)
        WMI_MAP.put("XXK", "Kavz");

        // Kawasaki (23 codes)
        WMI_MAP.put("JKR", "Kawasaki");
        WMI_MAP.put("ZKA", "Kawasaki");
        WMI_MAP.put("ZKB", "Kawasaki");
        WMI_MAP.put("ZKC", "Kawasaki");
        WMI_MAP.put("ZKD", "Kawasaki");
        WMI_MAP.put("ZKE", "Kawasaki");
        WMI_MAP.put("ZKF", "Kawasaki");
        WMI_MAP.put("ZKG", "Kawasaki");
        WMI_MAP.put("ZKJ", "Kawasaki");
        WMI_MAP.put("ZKK", "Kawasaki");
        WMI_MAP.put("ZKL", "Kawasaki");
        WMI_MAP.put("ZKM", "Kawasaki");
        WMI_MAP.put("ZKN", "Kawasaki");
        WMI_MAP.put("ZKP", "Kawasaki");
        WMI_MAP.put("ZKR", "Kawasaki");
        WMI_MAP.put("ZKS", "Kawasaki");
        WMI_MAP.put("ZKT", "Kawasaki");
        WMI_MAP.put("ZKU", "Kawasaki");
        WMI_MAP.put("ZKV", "Kawasaki");
        WMI_MAP.put("ZKW", "Kawasaki");
        WMI_MAP.put("ZKX", "Kawasaki");
        WMI_MAP.put("ZKY", "Kawasaki");
        WMI_MAP.put("ZKZ", "Kawasaki");

        // Kia (37 codes)
        WMI_MAP.put("3KP", "Kia");
        WMI_MAP.put("5KJ", "Kia");
        WMI_MAP.put("5KK", "Kia");
        WMI_MAP.put("5KM", "Kia");
        WMI_MAP.put("5XX", "Kia");
        WMI_MAP.put("5XY", "Kia");
        WMI_MAP.put("KN1", "Kia");
        WMI_MAP.put("KN2", "Kia");
        WMI_MAP.put("KN3", "Kia");
        WMI_MAP.put("KN4", "Kia");
        WMI_MAP.put("KN5", "Kia");
        WMI_MAP.put("KN6", "Kia");
        WMI_MAP.put("KN7", "Kia");
        WMI_MAP.put("KN8", "Kia");
        WMI_MAP.put("KN9", "Kia");
        WMI_MAP.put("KNA", "Kia");
        WMI_MAP.put("KNB", "Kia");
        WMI_MAP.put("KNC", "Kia");
        WMI_MAP.put("KND", "Kia");
        WMI_MAP.put("KNE", "Kia");
        WMI_MAP.put("KNF", "Kia");
        WMI_MAP.put("KNG", "Kia");
        WMI_MAP.put("KNH", "Kia");
        WMI_MAP.put("KNJ", "Kia");
        WMI_MAP.put("KNK", "Kia");
        WMI_MAP.put("KNL", "Kia");
        WMI_MAP.put("KNM", "Kia");
        WMI_MAP.put("KNN", "Kia");
        WMI_MAP.put("KNP", "Kia");
        WMI_MAP.put("KNR", "Kia");
        WMI_MAP.put("KNT", "Kia");
        WMI_MAP.put("KNU", "Kia");
        WMI_MAP.put("X9P", "Kia");
        WMI_MAP.put("XEE", "Kia");
        WMI_MAP.put("XKU", "Kia");
        WMI_MAP.put("XU5", "Kia");
        WMI_MAP.put("XWE", "Kia");

        // Koenigsegg (2 codes)
        WMI_MAP.put("SJK", "Koenigsegg");
        WMI_MAP.put("YT9", "Koenigsegg");

        // Kässbohrer (1 codes)
        WMI_MAP.put("WKK", "Kässbohrer");

        // LDV (1 codes)
        WMI_MAP.put("SEY", "LDV");

        // Lada (8 codes)
        WMI_MAP.put("X1E", "Lada");
        WMI_MAP.put("X3L", "Lada");
        WMI_MAP.put("XSU", "Lada");
        WMI_MAP.put("XT3", "Lada");
        WMI_MAP.put("XTA", "Lada");
        WMI_MAP.put("XTR", "Lada");
        WMI_MAP.put("XYL", "Lada");
        WMI_MAP.put("XZ9", "Lada");

        // Lamborghini (2 codes)
        WMI_MAP.put("LZJ", "Lamborghini");
        WMI_MAP.put("ZHW", "Lamborghini");

        // Lancia (23 codes)
        WMI_MAP.put("ZLA", "Lancia");
        WMI_MAP.put("ZLB", "Lancia");
        WMI_MAP.put("ZLC", "Lancia");
        WMI_MAP.put("ZLD", "Lancia");
        WMI_MAP.put("ZLE", "Lancia");
        WMI_MAP.put("ZLF", "Lancia");
        WMI_MAP.put("ZLG", "Lancia");
        WMI_MAP.put("ZLH", "Lancia");
        WMI_MAP.put("ZLJ", "Lancia");
        WMI_MAP.put("ZLK", "Lancia");
        WMI_MAP.put("ZLL", "Lancia");
        WMI_MAP.put("ZLM", "Lancia");
        WMI_MAP.put("ZLN", "Lancia");
        WMI_MAP.put("ZLP", "Lancia");
        WMI_MAP.put("ZLR", "Lancia");
        WMI_MAP.put("ZLS", "Lancia");
        WMI_MAP.put("ZLT", "Lancia");
        WMI_MAP.put("ZLU", "Lancia");
        WMI_MAP.put("ZLV", "Lancia");
        WMI_MAP.put("ZLW", "Lancia");
        WMI_MAP.put("ZLX", "Lancia");
        WMI_MAP.put("ZLY", "Lancia");
        WMI_MAP.put("ZLZ", "Lancia");

        // Land Rover (5 codes)
        WMI_MAP.put("LRZ", "Land Rover");
        WMI_MAP.put("SAL", "Land Rover");
        WMI_MAP.put("SAM", "Land Rover");
        WMI_MAP.put("SAN", "Land Rover");
        WMI_MAP.put("SAP", "Land Rover");

        // Laverda (2 codes)
        WMI_MAP.put("ZDW", "Laverda");
        WMI_MAP.put("ZEL", "Laverda");

        // Leopaard (1 codes)
        WMI_MAP.put("LZF", "Leopaard");

        // Lexus (4 codes)
        WMI_MAP.put("5TX", "Lexus");
        WMI_MAP.put("JT8", "Lexus");
        WMI_MAP.put("JTH", "Lexus");
        WMI_MAP.put("JTJ", "Lexus");

        // Li Auto (1 codes)
        WMI_MAP.put("LYL", "Li Auto");

        // Lifan (25 codes)
        WMI_MAP.put("LL3", "Lifan");
        WMI_MAP.put("LL8", "Lifan");
        WMI_MAP.put("LLB", "Lifan");
        WMI_MAP.put("LLC", "Lifan");
        WMI_MAP.put("LLD", "Lifan");
        WMI_MAP.put("LLE", "Lifan");
        WMI_MAP.put("LLF", "Lifan");
        WMI_MAP.put("LLG", "Lifan");
        WMI_MAP.put("LLH", "Lifan");
        WMI_MAP.put("LLJ", "Lifan");
        WMI_MAP.put("LLK", "Lifan");
        WMI_MAP.put("LLL", "Lifan");
        WMI_MAP.put("LLM", "Lifan");
        WMI_MAP.put("LLN", "Lifan");
        WMI_MAP.put("LLP", "Lifan");
        WMI_MAP.put("LLR", "Lifan");
        WMI_MAP.put("LLS", "Lifan");
        WMI_MAP.put("LLT", "Lifan");
        WMI_MAP.put("LLU", "Lifan");
        WMI_MAP.put("LLV", "Lifan");
        WMI_MAP.put("LLW", "Lifan");
        WMI_MAP.put("LLX", "Lifan");
        WMI_MAP.put("LLY", "Lifan");
        WMI_MAP.put("LLZ", "Lifan");
        WMI_MAP.put("LSF", "Lifan");

        // Ligier (1 codes)
        WMI_MAP.put("VFM", "Ligier");

        // Lincoln (12 codes)
        WMI_MAP.put("1L1", "Lincoln");
        WMI_MAP.put("1L5", "Lincoln");
        WMI_MAP.put("1LN", "Lincoln");
        WMI_MAP.put("2L3", "Lincoln");
        WMI_MAP.put("2LM", "Lincoln");
        WMI_MAP.put("3LN", "Lincoln");
        WMI_MAP.put("5L1", "Lincoln");
        WMI_MAP.put("5L3", "Lincoln");
        WMI_MAP.put("5L4", "Lincoln");
        WMI_MAP.put("5LM", "Lincoln");
        WMI_MAP.put("5LT", "Lincoln");
        WMI_MAP.put("LZR", "Lincoln");

        // Lotus (5 codes)
        WMI_MAP.put("LPE", "Lotus");
        WMI_MAP.put("LT1", "Lotus");
        WMI_MAP.put("SCC", "Lotus");
        WMI_MAP.put("SUL", "Lotus");
        WMI_MAP.put("SUU", "Lotus");

        // Lynk & Co (3 codes)
        WMI_MAP.put("LT3", "Lynk & Co");
        WMI_MAP.put("LYC", "Lynk & Co");
        WMI_MAP.put("LYK", "Lynk & Co");

        // MAN (21 codes)
        WMI_MAP.put("LZM", "MAN");
        WMI_MAP.put("MAN", "MAN");
        WMI_MAP.put("WMA", "MAN");
        WMI_MAP.put("WMB", "MAN");
        WMI_MAP.put("WMC", "MAN");
        WMI_MAP.put("WMD", "MAN");
        WMI_MAP.put("WMH", "MAN");
        WMI_MAP.put("WMJ", "MAN");
        WMI_MAP.put("WMK", "MAN");
        WMI_MAP.put("WML", "MAN");
        WMI_MAP.put("WMM", "MAN");
        WMI_MAP.put("WMN", "MAN");
        WMI_MAP.put("WMP", "MAN");
        WMI_MAP.put("WMR", "MAN");
        WMI_MAP.put("WMS", "MAN");
        WMI_MAP.put("WMT", "MAN");
        WMI_MAP.put("WMU", "MAN");
        WMI_MAP.put("WMV", "MAN");
        WMI_MAP.put("WMY", "MAN");
        WMI_MAP.put("WMZ", "MAN");
        WMI_MAP.put("YLR", "MAN");

        // MBK (18 codes)
        WMI_MAP.put("VG5", "MBK");
        WMI_MAP.put("VGA", "MBK");
        WMI_MAP.put("VGB", "MBK");
        WMI_MAP.put("VGC", "MBK");
        WMI_MAP.put("VGJ", "MBK");
        WMI_MAP.put("VGK", "MBK");
        WMI_MAP.put("VGM", "MBK");
        WMI_MAP.put("VGN", "MBK");
        WMI_MAP.put("VGP", "MBK");
        WMI_MAP.put("VGR", "MBK");
        WMI_MAP.put("VGS", "MBK");
        WMI_MAP.put("VGT", "MBK");
        WMI_MAP.put("VGU", "MBK");
        WMI_MAP.put("VGV", "MBK");
        WMI_MAP.put("VGW", "MBK");
        WMI_MAP.put("VGX", "MBK");
        WMI_MAP.put("VGY", "MBK");
        WMI_MAP.put("VGZ", "MBK");

        // MG (3 codes)
        WMI_MAP.put("LSY", "MG");
        WMI_MAP.put("SRF", "MG");
        WMI_MAP.put("SRH", "MG");

        // MG Rover (1 codes)
        WMI_MAP.put("SAV", "MG Rover");

        // MV Agusta (1 codes)
        WMI_MAP.put("ZDZ", "MV Agusta");

        // MZ (1 codes)
        WMI_MAP.put("YCZ", "MZ");

        // Mack (1 codes)
        WMI_MAP.put("6AC", "Mack");

        // Mahindra (3 codes)
        WMI_MAP.put("MA1", "Mahindra");
        WMI_MAP.put("MAX", "Mahindra");
        WMI_MAP.put("MBX", "Mahindra");

        // Maico (1 codes)
        WMI_MAP.put("ZEJ", "Maico");

        // Malaguti (2 codes)
        WMI_MAP.put("Z8M", "Malaguti");
        WMI_MAP.put("ZEM", "Malaguti");

        // Maruti (1 codes)
        WMI_MAP.put("MAR", "Maruti");

        // Maserati (34 codes)
        WMI_MAP.put("ZAM", "Maserati");
        WMI_MAP.put("ZAN", "Maserati");
        WMI_MAP.put("ZN1", "Maserati");
        WMI_MAP.put("ZN2", "Maserati");
        WMI_MAP.put("ZN3", "Maserati");
        WMI_MAP.put("ZN4", "Maserati");
        WMI_MAP.put("ZN5", "Maserati");
        WMI_MAP.put("ZN6", "Maserati");
        WMI_MAP.put("ZN7", "Maserati");
        WMI_MAP.put("ZN8", "Maserati");
        WMI_MAP.put("ZN9", "Maserati");
        WMI_MAP.put("ZNA", "Maserati");
        WMI_MAP.put("ZNB", "Maserati");
        WMI_MAP.put("ZNC", "Maserati");
        WMI_MAP.put("ZND", "Maserati");
        WMI_MAP.put("ZNE", "Maserati");
        WMI_MAP.put("ZNF", "Maserati");
        WMI_MAP.put("ZNG", "Maserati");
        WMI_MAP.put("ZNH", "Maserati");
        WMI_MAP.put("ZNJ", "Maserati");
        WMI_MAP.put("ZNK", "Maserati");
        WMI_MAP.put("ZNL", "Maserati");
        WMI_MAP.put("ZNM", "Maserati");
        WMI_MAP.put("ZNN", "Maserati");
        WMI_MAP.put("ZNP", "Maserati");
        WMI_MAP.put("ZNR", "Maserati");
        WMI_MAP.put("ZNS", "Maserati");
        WMI_MAP.put("ZNT", "Maserati");
        WMI_MAP.put("ZNU", "Maserati");
        WMI_MAP.put("ZNV", "Maserati");
        WMI_MAP.put("ZNW", "Maserati");
        WMI_MAP.put("ZNX", "Maserati");
        WMI_MAP.put("ZNY", "Maserati");
        WMI_MAP.put("ZNZ", "Maserati");

        // Matra (1 codes)
        WMI_MAP.put("VF8", "Matra");

        // Maybach (2 codes)
        WMI_MAP.put("SCD", "Maybach");
        WMI_MAP.put("WEB", "Maybach");

        // Mazda (24 codes)
        WMI_MAP.put("1Y1", "Mazda");
        WMI_MAP.put("1YV", "Mazda");
        WMI_MAP.put("3MD", "Mazda");
        WMI_MAP.put("3MY", "Mazda");
        WMI_MAP.put("4F2", "Mazda");
        WMI_MAP.put("4F3", "Mazda");
        WMI_MAP.put("4F4", "Mazda");
        WMI_MAP.put("5YV", "Mazda");
        WMI_MAP.put("6MZ", "Mazda");
        WMI_MAP.put("JC1", "Mazda");
        WMI_MAP.put("JG1", "Mazda");
        WMI_MAP.put("JG7", "Mazda");
        WMI_MAP.put("JM1", "Mazda");
        WMI_MAP.put("JM3", "Mazda");
        WMI_MAP.put("JM6", "Mazda");
        WMI_MAP.put("JM7", "Mazda");
        WMI_MAP.put("JM8", "Mazda");
        WMI_MAP.put("JMZ", "Mazda");
        WMI_MAP.put("LP5", "Mazda");
        WMI_MAP.put("LVR", "Mazda");
        WMI_MAP.put("SYA", "Mazda");
        WMI_MAP.put("SYE", "Mazda");
        WMI_MAP.put("XTY", "Mazda");
        WMI_MAP.put("YCM", "Mazda");

        // McLaren (1 codes)
        WMI_MAP.put("SBM", "McLaren");

        // Mega (1 codes)
        WMI_MAP.put("VGH", "Mega");

        // Mercedes-Benz (45 codes)
        WMI_MAP.put("4JG", "Mercedes-Benz");
        WMI_MAP.put("4JP", "Mercedes-Benz");
        WMI_MAP.put("9BM", "Mercedes-Benz");
        WMI_MAP.put("MBR", "Mercedes-Benz");
        WMI_MAP.put("SDC", "Mercedes-Benz");
        WMI_MAP.put("VSA", "Mercedes-Benz");
        WMI_MAP.put("VUF", "Mercedes-Benz");
        WMI_MAP.put("VUM", "Mercedes-Benz");
        WMI_MAP.put("VUN", "Mercedes-Benz");
        WMI_MAP.put("WD0", "Mercedes-Benz");
        WMI_MAP.put("WD1", "Mercedes-Benz");
        WMI_MAP.put("WD2", "Mercedes-Benz");
        WMI_MAP.put("WD3", "Mercedes-Benz");
        WMI_MAP.put("WD4", "Mercedes-Benz");
        WMI_MAP.put("WD5", "Mercedes-Benz");
        WMI_MAP.put("WD6", "Mercedes-Benz");
        WMI_MAP.put("WD7", "Mercedes-Benz");
        WMI_MAP.put("WD8", "Mercedes-Benz");
        WMI_MAP.put("WD9", "Mercedes-Benz");
        WMI_MAP.put("WDA", "Mercedes-Benz");
        WMI_MAP.put("WDB", "Mercedes-Benz");
        WMI_MAP.put("WDC", "Mercedes-Benz");
        WMI_MAP.put("WDD", "Mercedes-Benz");
        WMI_MAP.put("WDE", "Mercedes-Benz");
        WMI_MAP.put("WDF", "Mercedes-Benz");
        WMI_MAP.put("WDG", "Mercedes-Benz");
        WMI_MAP.put("WDH", "Mercedes-Benz");
        WMI_MAP.put("WDJ", "Mercedes-Benz");
        WMI_MAP.put("WDK", "Mercedes-Benz");
        WMI_MAP.put("WDL", "Mercedes-Benz");
        WMI_MAP.put("WDM", "Mercedes-Benz");
        WMI_MAP.put("WDN", "Mercedes-Benz");
        WMI_MAP.put("WDP", "Mercedes-Benz");
        WMI_MAP.put("WDR", "Mercedes-Benz");
        WMI_MAP.put("WDS", "Mercedes-Benz");
        WMI_MAP.put("WDT", "Mercedes-Benz");
        WMI_MAP.put("WDU", "Mercedes-Benz");
        WMI_MAP.put("WDV", "Mercedes-Benz");
        WMI_MAP.put("WDW", "Mercedes-Benz");
        WMI_MAP.put("WDX", "Mercedes-Benz");
        WMI_MAP.put("WDY", "Mercedes-Benz");
        WMI_MAP.put("WDZ", "Mercedes-Benz");
        WMI_MAP.put("WEA", "Mercedes-Benz");
        WMI_MAP.put("WMF", "Mercedes-Benz");
        WMI_MAP.put("WMX", "Mercedes-Benz");

        // Mercury (16 codes)
        WMI_MAP.put("1M1", "Mercury");
        WMI_MAP.put("1M2", "Mercury");
        WMI_MAP.put("1M3", "Mercury");
        WMI_MAP.put("1M4", "Mercury");
        WMI_MAP.put("1M8", "Mercury");
        WMI_MAP.put("1ME", "Mercury");
        WMI_MAP.put("1MR", "Mercury");
        WMI_MAP.put("2M1", "Mercury");
        WMI_MAP.put("2M2", "Mercury");
        WMI_MAP.put("2M3", "Mercury");
        WMI_MAP.put("2M4", "Mercury");
        WMI_MAP.put("2M5", "Mercury");
        WMI_MAP.put("2M6", "Mercury");
        WMI_MAP.put("2ME", "Mercury");
        WMI_MAP.put("3ME", "Mercury");
        WMI_MAP.put("4M2", "Mercury");

        // Mini (1 codes)
        WMI_MAP.put("WMW", "Mini");

        // Mitsubishi (22 codes)
        WMI_MAP.put("4A3", "Mitsubishi");
        WMI_MAP.put("4A4", "Mitsubishi");
        WMI_MAP.put("4A5", "Mitsubishi");
        WMI_MAP.put("4B3", "Mitsubishi");
        WMI_MAP.put("6F5", "Mitsubishi");
        WMI_MAP.put("6MM", "Mitsubishi");
        WMI_MAP.put("6MP", "Mitsubishi");
        WMI_MAP.put("93X", "Mitsubishi");
        WMI_MAP.put("JA3", "Mitsubishi");
        WMI_MAP.put("JA4", "Mitsubishi");
        WMI_MAP.put("JA7", "Mitsubishi");
        WMI_MAP.put("JL5", "Mitsubishi");
        WMI_MAP.put("JL6", "Mitsubishi");
        WMI_MAP.put("JL7", "Mitsubishi");
        WMI_MAP.put("JMB", "Mitsubishi");
        WMI_MAP.put("JMY", "Mitsubishi");
        WMI_MAP.put("KPH", "Mitsubishi");
        WMI_MAP.put("MA7", "Mitsubishi");
        WMI_MAP.put("MCM", "Mitsubishi");
        WMI_MAP.put("X7M", "Mitsubishi");
        WMI_MAP.put("XMC", "Mitsubishi");
        WMI_MAP.put("XUN", "Mitsubishi");

        // Morgan (1 codes)
        WMI_MAP.put("SA9", "Morgan");

        // Morris (1 codes)
        WMI_MAP.put("SAW", "Morris");

        // Moskvich (1 codes)
        WMI_MAP.put("XVL", "Moskvich");

        // Moto Guzzi (5 codes)
        WMI_MAP.put("ZDN", "Moto Guzzi");
        WMI_MAP.put("ZEG", "Moto Guzzi");
        WMI_MAP.put("ZEN", "Moto Guzzi");
        WMI_MAP.put("ZGG", "Moto Guzzi");
        WMI_MAP.put("ZGU", "Moto Guzzi");

        // Moto Morini (1 codes)
        WMI_MAP.put("ZDP", "Moto Morini");

        // Neoplan (1 codes)
        WMI_MAP.put("WKA", "Neoplan");

        // Nio (1 codes)
        WMI_MAP.put("LZW", "Nio");

        // Nissan (111 codes)
        WMI_MAP.put("1N4", "Nissan");
        WMI_MAP.put("1N6", "Nissan");
        WMI_MAP.put("1N8", "Nissan");
        WMI_MAP.put("1N9", "Nissan");
        WMI_MAP.put("1NP", "Nissan");
        WMI_MAP.put("2NV", "Nissan");
        WMI_MAP.put("3N1", "Nissan");
        WMI_MAP.put("3N6", "Nissan");
        WMI_MAP.put("3N8", "Nissan");
        WMI_MAP.put("3NV", "Nissan");
        WMI_MAP.put("5N1", "Nissan");
        WMI_MAP.put("6F4", "Nissan");
        WMI_MAP.put("94D", "Nissan");
        WMI_MAP.put("JN1", "Nissan");
        WMI_MAP.put("JN3", "Nissan");
        WMI_MAP.put("JN4", "Nissan");
        WMI_MAP.put("JN6", "Nissan");
        WMI_MAP.put("JN8", "Nissan");
        WMI_MAP.put("JNA", "Nissan");
        WMI_MAP.put("JNB", "Nissan");
        WMI_MAP.put("JNC", "Nissan");
        WMI_MAP.put("JND", "Nissan");
        WMI_MAP.put("JNE", "Nissan");
        WMI_MAP.put("JNF", "Nissan");
        WMI_MAP.put("JNT", "Nissan");
        WMI_MAP.put("JNV", "Nissan");
        WMI_MAP.put("JNZ", "Nissan");
        WMI_MAP.put("LDN", "Nissan");
        WMI_MAP.put("LDP", "Nissan");
        WMI_MAP.put("LDT", "Nissan");
        WMI_MAP.put("LDV", "Nissan");
        WMI_MAP.put("LGB", "Nissan");
        WMI_MAP.put("LN8", "Nissan");
        WMI_MAP.put("LNA", "Nissan");
        WMI_MAP.put("LNB", "Nissan");
        WMI_MAP.put("LNC", "Nissan");
        WMI_MAP.put("LND", "Nissan");
        WMI_MAP.put("LNE", "Nissan");
        WMI_MAP.put("LNF", "Nissan");
        WMI_MAP.put("LNG", "Nissan");
        WMI_MAP.put("LNH", "Nissan");
        WMI_MAP.put("LNJ", "Nissan");
        WMI_MAP.put("LNK", "Nissan");
        WMI_MAP.put("LNL", "Nissan");
        WMI_MAP.put("LNM", "Nissan");
        WMI_MAP.put("LNN", "Nissan");
        WMI_MAP.put("LNP", "Nissan");
        WMI_MAP.put("LNR", "Nissan");
        WMI_MAP.put("LNS", "Nissan");
        WMI_MAP.put("LNT", "Nissan");
        WMI_MAP.put("LNU", "Nissan");
        WMI_MAP.put("LNV", "Nissan");
        WMI_MAP.put("LNW", "Nissan");
        WMI_MAP.put("LNX", "Nissan");
        WMI_MAP.put("LNY", "Nissan");
        WMI_MAP.put("LNZ", "Nissan");
        WMI_MAP.put("MCD", "Nissan");
        WMI_MAP.put("MCG", "Nissan");
        WMI_MAP.put("SD2", "Nissan");
        WMI_MAP.put("SJD", "Nissan");
        WMI_MAP.put("SJN", "Nissan");
        WMI_MAP.put("SN1", "Nissan");
        WMI_MAP.put("SN3", "Nissan");
        WMI_MAP.put("SN4", "Nissan");
        WMI_MAP.put("SN6", "Nissan");
        WMI_MAP.put("SN8", "Nissan");
        WMI_MAP.put("SNC", "Nissan");
        WMI_MAP.put("SUN", "Nissan");
        WMI_MAP.put("VLG", "Nissan");
        WMI_MAP.put("VN1", "Nissan");
        WMI_MAP.put("VN2", "Nissan");
        WMI_MAP.put("VN3", "Nissan");
        WMI_MAP.put("VN4", "Nissan");
        WMI_MAP.put("VN5", "Nissan");
        WMI_MAP.put("VN6", "Nissan");
        WMI_MAP.put("VN7", "Nissan");
        WMI_MAP.put("VN8", "Nissan");
        WMI_MAP.put("VN9", "Nissan");
        WMI_MAP.put("VNA", "Nissan");
        WMI_MAP.put("VNB", "Nissan");
        WMI_MAP.put("VNC", "Nissan");
        WMI_MAP.put("VND", "Nissan");
        WMI_MAP.put("VNF", "Nissan");
        WMI_MAP.put("VNG", "Nissan");
        WMI_MAP.put("VNH", "Nissan");
        WMI_MAP.put("VNJ", "Nissan");
        WMI_MAP.put("VNL", "Nissan");
        WMI_MAP.put("VNM", "Nissan");
        WMI_MAP.put("VNN", "Nissan");
        WMI_MAP.put("VNP", "Nissan");
        WMI_MAP.put("VNR", "Nissan");
        WMI_MAP.put("VNS", "Nissan");
        WMI_MAP.put("VNT", "Nissan");
        WMI_MAP.put("VNU", "Nissan");
        WMI_MAP.put("VNV", "Nissan");
        WMI_MAP.put("VNW", "Nissan");
        WMI_MAP.put("VNX", "Nissan");
        WMI_MAP.put("VNY", "Nissan");
        WMI_MAP.put("VNZ", "Nissan");
        WMI_MAP.put("VS7", "Nissan");
        WMI_MAP.put("VSG", "Nissan");
        WMI_MAP.put("VSH", "Nissan");
        WMI_MAP.put("VSJ", "Nissan");
        WMI_MAP.put("VSK", "Nissan");
        WMI_MAP.put("VST", "Nissan");
        WMI_MAP.put("VSW", "Nissan");
        WMI_MAP.put("VTV", "Nissan");
        WMI_MAP.put("VTX", "Nissan");
        WMI_MAP.put("XD3", "Nissan");
        WMI_MAP.put("XMN", "Nissan");
        WMI_MAP.put("XWV", "Nissan");

        // Oldsmobile (3 codes)
        WMI_MAP.put("1G3", "Oldsmobile");
        WMI_MAP.put("2G3", "Oldsmobile");
        WMI_MAP.put("3G3", "Oldsmobile");

        // Opel (13 codes)
        WMI_MAP.put("LZ0", "Opel");
        WMI_MAP.put("LZA", "Opel");
        WMI_MAP.put("LZB", "Opel");
        WMI_MAP.put("LZC", "Opel");
        WMI_MAP.put("VSN", "Opel");
        WMI_MAP.put("VSX", "Opel");
        WMI_MAP.put("W0L", "Opel");
        WMI_MAP.put("W0P", "Opel");
        WMI_MAP.put("W0V", "Opel");
        WMI_MAP.put("W0X", "Opel");
        WMI_MAP.put("X8Z", "Opel");
        WMI_MAP.put("XWF", "Opel");
        WMI_MAP.put("XZU", "Opel");

        // Optare (1 codes)
        WMI_MAP.put("SAB", "Optare");

        // Otokar (3 codes)
        WMI_MAP.put("VS0", "Otokar");
        WMI_MAP.put("VS1", "Otokar");
        WMI_MAP.put("VS2", "Otokar");

        // PAZ (1 codes)
        WMI_MAP.put("X1M", "PAZ");

        // Peugeot (13 codes)
        WMI_MAP.put("936", "Peugeot");
        WMI_MAP.put("LP3", "Peugeot");
        WMI_MAP.put("SBB", "Peugeot");
        WMI_MAP.put("VF3", "Peugeot");
        WMI_MAP.put("VF4", "Peugeot");
        WMI_MAP.put("VFD", "Peugeot");
        WMI_MAP.put("VFF", "Peugeot");
        WMI_MAP.put("VFH", "Peugeot");
        WMI_MAP.put("VFP", "Peugeot");
        WMI_MAP.put("VLF", "Peugeot");
        WMI_MAP.put("VSB", "Peugeot");
        WMI_MAP.put("X8E", "Peugeot");
        WMI_MAP.put("ZEP", "Peugeot");

        // Piaggio (12 codes)
        WMI_MAP.put("MEE", "Piaggio");
        WMI_MAP.put("ZEE", "Piaggio");
        WMI_MAP.put("ZHN", "Piaggio");
        WMI_MAP.put("ZHP", "Piaggio");
        WMI_MAP.put("ZHR", "Piaggio");
        WMI_MAP.put("ZHS", "Piaggio");
        WMI_MAP.put("ZHT", "Piaggio");
        WMI_MAP.put("ZJM", "Piaggio");
        WMI_MAP.put("ZJP", "Piaggio");
        WMI_MAP.put("ZJR", "Piaggio");
        WMI_MAP.put("ZJS", "Piaggio");
        WMI_MAP.put("ZJT", "Piaggio");

        // Plymouth (15 codes)
        WMI_MAP.put("1P3", "Plymouth");
        WMI_MAP.put("1P4", "Plymouth");
        WMI_MAP.put("1P5", "Plymouth");
        WMI_MAP.put("1P6", "Plymouth");
        WMI_MAP.put("1P7", "Plymouth");
        WMI_MAP.put("1P8", "Plymouth");
        WMI_MAP.put("1P9", "Plymouth");
        WMI_MAP.put("2P3", "Plymouth");
        WMI_MAP.put("2P4", "Plymouth");
        WMI_MAP.put("2P5", "Plymouth");
        WMI_MAP.put("2P6", "Plymouth");
        WMI_MAP.put("2P7", "Plymouth");
        WMI_MAP.put("2P8", "Plymouth");
        WMI_MAP.put("2P9", "Plymouth");
        WMI_MAP.put("3P3", "Plymouth");

        // Polestar (1 codes)
        WMI_MAP.put("LPS", "Polestar");

        // Pontiac (9 codes)
        WMI_MAP.put("1G2", "Pontiac");
        WMI_MAP.put("1GM", "Pontiac");
        WMI_MAP.put("2G2", "Pontiac");
        WMI_MAP.put("2G7", "Pontiac");
        WMI_MAP.put("3G2", "Pontiac");
        WMI_MAP.put("3GM", "Pontiac");
        WMI_MAP.put("6G2", "Pontiac");
        WMI_MAP.put("JPT", "Pontiac");
        WMI_MAP.put("KL2", "Pontiac");

        // Porsche (4 codes)
        WMI_MAP.put("LZU", "Porsche");
        WMI_MAP.put("WP0", "Porsche");
        WMI_MAP.put("WP1", "Porsche");
        WMI_MAP.put("WPZ", "Porsche");

        // Privately Built (1 codes)
        WMI_MAP.put("6U9", "Privately Built");

        // Renault (23 codes)
        WMI_MAP.put("93Y", "Renault");
        WMI_MAP.put("MCE", "Renault");
        WMI_MAP.put("VF1", "Renault");
        WMI_MAP.put("VF2", "Renault");
        WMI_MAP.put("VF5", "Renault");
        WMI_MAP.put("VF6", "Renault");
        WMI_MAP.put("VFK", "Renault");
        WMI_MAP.put("VFL", "Renault");
        WMI_MAP.put("VFN", "Renault");
        WMI_MAP.put("VFR", "Renault");
        WMI_MAP.put("VFS", "Renault");
        WMI_MAP.put("VFV", "Renault");
        WMI_MAP.put("VFW", "Renault");
        WMI_MAP.put("VSR", "Renault");
        WMI_MAP.put("VSY", "Renault");
        WMI_MAP.put("X5L", "Renault");
        WMI_MAP.put("X7A", "Renault");
        WMI_MAP.put("X7J", "Renault");
        WMI_MAP.put("X7L", "Renault");
        WMI_MAP.put("XMA", "Renault");
        WMI_MAP.put("XTH", "Renault");
        WMI_MAP.put("XUJ", "Renault");
        WMI_MAP.put("XWP", "Renault");

        // Rolls-Royce (3 codes)
        WMI_MAP.put("SBC", "Rolls-Royce");
        WMI_MAP.put("SCA", "Rolls-Royce");
        WMI_MAP.put("SPV", "Rolls-Royce");

        // Rover (5 codes)
        WMI_MAP.put("SAR", "Rover");
        WMI_MAP.put("SD1", "Rover");
        WMI_MAP.put("SL0", "Rover");
        WMI_MAP.put("SRR", "Rover");
        WMI_MAP.put("VTL", "Rover");

        // Royal Enfield (20 codes)
        WMI_MAP.put("ME9", "Royal Enfield");
        WMI_MAP.put("MEA", "Royal Enfield");
        WMI_MAP.put("MEB", "Royal Enfield");
        WMI_MAP.put("MEC", "Royal Enfield");
        WMI_MAP.put("MED", "Royal Enfield");
        WMI_MAP.put("MEH", "Royal Enfield");
        WMI_MAP.put("MEJ", "Royal Enfield");
        WMI_MAP.put("MEK", "Royal Enfield");
        WMI_MAP.put("MEL", "Royal Enfield");
        WMI_MAP.put("MEM", "Royal Enfield");
        WMI_MAP.put("MEN", "Royal Enfield");
        WMI_MAP.put("MEP", "Royal Enfield");
        WMI_MAP.put("MER", "Royal Enfield");
        WMI_MAP.put("MES", "Royal Enfield");
        WMI_MAP.put("MET", "Royal Enfield");
        WMI_MAP.put("MEU", "Royal Enfield");
        WMI_MAP.put("MEV", "Royal Enfield");
        WMI_MAP.put("MEW", "Royal Enfield");
        WMI_MAP.put("MEY", "Royal Enfield");
        WMI_MAP.put("MEZ", "Royal Enfield");

        // SAIC (47 codes)
        WMI_MAP.put("LM5", "SAIC");
        WMI_MAP.put("LMD", "SAIC");
        WMI_MAP.put("LME", "SAIC");
        WMI_MAP.put("LMF", "SAIC");
        WMI_MAP.put("LMH", "SAIC");
        WMI_MAP.put("LMJ", "SAIC");
        WMI_MAP.put("LMK", "SAIC");
        WMI_MAP.put("LML", "SAIC");
        WMI_MAP.put("LMN", "SAIC");
        WMI_MAP.put("LMP", "SAIC");
        WMI_MAP.put("LMR", "SAIC");
        WMI_MAP.put("LMS", "SAIC");
        WMI_MAP.put("LMT", "SAIC");
        WMI_MAP.put("LMU", "SAIC");
        WMI_MAP.put("LMV", "SAIC");
        WMI_MAP.put("LMW", "SAIC");
        WMI_MAP.put("LMX", "SAIC");
        WMI_MAP.put("LMY", "SAIC");
        WMI_MAP.put("LMZ", "SAIC");
        WMI_MAP.put("LS1", "SAIC");
        WMI_MAP.put("LS2", "SAIC");
        WMI_MAP.put("LS3", "SAIC");
        WMI_MAP.put("LS4", "SAIC");
        WMI_MAP.put("LS6", "SAIC");
        WMI_MAP.put("LS7", "SAIC");
        WMI_MAP.put("LS8", "SAIC");
        WMI_MAP.put("LS9", "SAIC");
        WMI_MAP.put("LSA", "SAIC");
        WMI_MAP.put("LSB", "SAIC");
        WMI_MAP.put("LSC", "SAIC");
        WMI_MAP.put("LSD", "SAIC");
        WMI_MAP.put("LSE", "SAIC");
        WMI_MAP.put("LSG", "SAIC");
        WMI_MAP.put("LSH", "SAIC");
        WMI_MAP.put("LSJ", "SAIC");
        WMI_MAP.put("LSK", "SAIC");
        WMI_MAP.put("LSL", "SAIC");
        WMI_MAP.put("LSM", "SAIC");
        WMI_MAP.put("LSN", "SAIC");
        WMI_MAP.put("LSP", "SAIC");
        WMI_MAP.put("LSR", "SAIC");
        WMI_MAP.put("LSS", "SAIC");
        WMI_MAP.put("LST", "SAIC");
        WMI_MAP.put("LSU", "SAIC");
        WMI_MAP.put("LSW", "SAIC");
        WMI_MAP.put("LSX", "SAIC");
        WMI_MAP.put("LSZ", "SAIC");

        // SEG (1 codes)
        WMI_MAP.put("WCH", "SEG");

        // SSC (1 codes)
        WMI_MAP.put("SSC", "SSC");

        // Saab (47 codes)
        WMI_MAP.put("VTG", "Saab");
        WMI_MAP.put("YE1", "Saab");
        WMI_MAP.put("YED", "Saab");
        WMI_MAP.put("YK1", "Saab");
        WMI_MAP.put("YK2", "Saab");
        WMI_MAP.put("YK3", "Saab");
        WMI_MAP.put("YK4", "Saab");
        WMI_MAP.put("YK5", "Saab");
        WMI_MAP.put("YK6", "Saab");
        WMI_MAP.put("YK7", "Saab");
        WMI_MAP.put("YK8", "Saab");
        WMI_MAP.put("YK9", "Saab");
        WMI_MAP.put("YKA", "Saab");
        WMI_MAP.put("YKB", "Saab");
        WMI_MAP.put("YKC", "Saab");
        WMI_MAP.put("YKD", "Saab");
        WMI_MAP.put("YKE", "Saab");
        WMI_MAP.put("YKF", "Saab");
        WMI_MAP.put("YKG", "Saab");
        WMI_MAP.put("YKH", "Saab");
        WMI_MAP.put("YKJ", "Saab");
        WMI_MAP.put("YKK", "Saab");
        WMI_MAP.put("YKL", "Saab");
        WMI_MAP.put("YKM", "Saab");
        WMI_MAP.put("YKN", "Saab");
        WMI_MAP.put("YKP", "Saab");
        WMI_MAP.put("YKR", "Saab");
        WMI_MAP.put("YKS", "Saab");
        WMI_MAP.put("YKT", "Saab");
        WMI_MAP.put("YKU", "Saab");
        WMI_MAP.put("YKV", "Saab");
        WMI_MAP.put("YKW", "Saab");
        WMI_MAP.put("YKX", "Saab");
        WMI_MAP.put("YKY", "Saab");
        WMI_MAP.put("YKZ", "Saab");
        WMI_MAP.put("YS3", "Saab");
        WMI_MAP.put("YTN", "Saab");
        WMI_MAP.put("YTP", "Saab");
        WMI_MAP.put("YTR", "Saab");
        WMI_MAP.put("YTS", "Saab");
        WMI_MAP.put("YTT", "Saab");
        WMI_MAP.put("YTU", "Saab");
        WMI_MAP.put("YTV", "Saab");
        WMI_MAP.put("YTW", "Saab");
        WMI_MAP.put("YTX", "Saab");
        WMI_MAP.put("YTY", "Saab");
        WMI_MAP.put("YTZ", "Saab");

        // Santana (15 codes)
        WMI_MAP.put("LZT", "Santana");
        WMI_MAP.put("VLA", "Santana");
        WMI_MAP.put("VLB", "Santana");
        WMI_MAP.put("VLC", "Santana");
        WMI_MAP.put("VLD", "Santana");
        WMI_MAP.put("VLK", "Santana");
        WMI_MAP.put("VLL", "Santana");
        WMI_MAP.put("VLM", "Santana");
        WMI_MAP.put("VLN", "Santana");
        WMI_MAP.put("VLP", "Santana");
        WMI_MAP.put("VLR", "Santana");
        WMI_MAP.put("VLT", "Santana");
        WMI_MAP.put("VSL", "Santana");
        WMI_MAP.put("VSU", "Santana");
        WMI_MAP.put("VUA", "Santana");

        // Saturn (1 codes)
        WMI_MAP.put("1G8", "Saturn");

        // Scania (47 codes)
        WMI_MAP.put("9BS", "Scania");
        WMI_MAP.put("SCG", "Scania");
        WMI_MAP.put("SCK", "Scania");
        WMI_MAP.put("SCL", "Scania");
        WMI_MAP.put("SCM", "Scania");
        WMI_MAP.put("SCN", "Scania");
        WMI_MAP.put("SCP", "Scania");
        WMI_MAP.put("SCR", "Scania");
        WMI_MAP.put("SCS", "Scania");
        WMI_MAP.put("SCT", "Scania");
        WMI_MAP.put("SCU", "Scania");
        WMI_MAP.put("SCV", "Scania");
        WMI_MAP.put("SCW", "Scania");
        WMI_MAP.put("SCX", "Scania");
        WMI_MAP.put("SCY", "Scania");
        WMI_MAP.put("SCZ", "Scania");
        WMI_MAP.put("YE2", "Scania");
        WMI_MAP.put("YS1", "Scania");
        WMI_MAP.put("YS2", "Scania");
        WMI_MAP.put("YS4", "Scania");
        WMI_MAP.put("YS5", "Scania");
        WMI_MAP.put("YS6", "Scania");
        WMI_MAP.put("YS7", "Scania");
        WMI_MAP.put("YS8", "Scania");
        WMI_MAP.put("YSA", "Scania");
        WMI_MAP.put("YSB", "Scania");
        WMI_MAP.put("YSC", "Scania");
        WMI_MAP.put("YSD", "Scania");
        WMI_MAP.put("YSE", "Scania");
        WMI_MAP.put("YSF", "Scania");
        WMI_MAP.put("YSG", "Scania");
        WMI_MAP.put("YSH", "Scania");
        WMI_MAP.put("YSJ", "Scania");
        WMI_MAP.put("YSK", "Scania");
        WMI_MAP.put("YSL", "Scania");
        WMI_MAP.put("YSM", "Scania");
        WMI_MAP.put("YSN", "Scania");
        WMI_MAP.put("YSP", "Scania");
        WMI_MAP.put("YSR", "Scania");
        WMI_MAP.put("YSS", "Scania");
        WMI_MAP.put("YST", "Scania");
        WMI_MAP.put("YSU", "Scania");
        WMI_MAP.put("YSV", "Scania");
        WMI_MAP.put("YSW", "Scania");
        WMI_MAP.put("YSX", "Scania");
        WMI_MAP.put("YSY", "Scania");
        WMI_MAP.put("YSZ", "Scania");

        // Schmitz (1 codes)
        WMI_MAP.put("WSM", "Schmitz");

        // Seat (26 codes)
        WMI_MAP.put("VLS", "Seat");
        WMI_MAP.put("VLU", "Seat");
        WMI_MAP.put("VLV", "Seat");
        WMI_MAP.put("VLW", "Seat");
        WMI_MAP.put("VLX", "Seat");
        WMI_MAP.put("VLY", "Seat");
        WMI_MAP.put("VLZ", "Seat");
        WMI_MAP.put("VS3", "Seat");
        WMI_MAP.put("VS4", "Seat");
        WMI_MAP.put("VS5", "Seat");
        WMI_MAP.put("VS8", "Seat");
        WMI_MAP.put("VSF", "Seat");
        WMI_MAP.put("VSM", "Seat");
        WMI_MAP.put("VSS", "Seat");
        WMI_MAP.put("VSZ", "Seat");
        WMI_MAP.put("VTS", "Seat");
        WMI_MAP.put("VTU", "Seat");
        WMI_MAP.put("VUR", "Seat");
        WMI_MAP.put("VUS", "Seat");
        WMI_MAP.put("VUT", "Seat");
        WMI_MAP.put("VUU", "Seat");
        WMI_MAP.put("VUV", "Seat");
        WMI_MAP.put("VUW", "Seat");
        WMI_MAP.put("VUX", "Seat");
        WMI_MAP.put("VUY", "Seat");
        WMI_MAP.put("VUZ", "Seat");

        // Skoda (2 codes)
        WMI_MAP.put("SSK", "Skoda");
        WMI_MAP.put("XZV", "Skoda");

        // Smart (3 codes)
        WMI_MAP.put("WEC", "Smart");
        WMI_MAP.put("WED", "Smart");
        WMI_MAP.put("WME", "Smart");

        // Solaris (1 codes)
        WMI_MAP.put("XU8", "Solaris");

        // SsangYong (2 codes)
        WMI_MAP.put("KPK", "SsangYong");
        WMI_MAP.put("KPT", "SsangYong");

        // Ssangyong (1 codes)
        WMI_MAP.put("X96", "Ssangyong");

        // Sterling (2 codes)
        WMI_MAP.put("2WL", "Sterling");
        WMI_MAP.put("JLS", "Sterling");

        // Subaru (7 codes)
        WMI_MAP.put("4S3", "Subaru");
        WMI_MAP.put("4S4", "Subaru");
        WMI_MAP.put("JF1", "Subaru");
        WMI_MAP.put("JF2", "Subaru");
        WMI_MAP.put("JF3", "Subaru");
        WMI_MAP.put("JF4", "Subaru");
        WMI_MAP.put("JF5", "Subaru");

        // Suzuki (31 codes)
        WMI_MAP.put("JKS", "Suzuki");
        WMI_MAP.put("JS1", "Suzuki");
        WMI_MAP.put("JS2", "Suzuki");
        WMI_MAP.put("JS3", "Suzuki");
        WMI_MAP.put("JS4", "Suzuki");
        WMI_MAP.put("JS5", "Suzuki");
        WMI_MAP.put("JS6", "Suzuki");
        WMI_MAP.put("JS7", "Suzuki");
        WMI_MAP.put("JS8", "Suzuki");
        WMI_MAP.put("JS9", "Suzuki");
        WMI_MAP.put("JSA", "Suzuki");
        WMI_MAP.put("JSB", "Suzuki");
        WMI_MAP.put("JSC", "Suzuki");
        WMI_MAP.put("JSD", "Suzuki");
        WMI_MAP.put("JSE", "Suzuki");
        WMI_MAP.put("JSG", "Suzuki");
        WMI_MAP.put("JSH", "Suzuki");
        WMI_MAP.put("JSJ", "Suzuki");
        WMI_MAP.put("JSK", "Suzuki");
        WMI_MAP.put("JSL", "Suzuki");
        WMI_MAP.put("JSZ", "Suzuki");
        WMI_MAP.put("LKL", "Suzuki");
        WMI_MAP.put("LKM", "Suzuki");
        WMI_MAP.put("LMC", "Suzuki");
        WMI_MAP.put("MA3", "Suzuki");
        WMI_MAP.put("MBS", "Suzuki");
        WMI_MAP.put("MCS", "Suzuki");
        WMI_MAP.put("VSE", "Suzuki");
        WMI_MAP.put("VTJ", "Suzuki");
        WMI_MAP.put("VTT", "Suzuki");
        WMI_MAP.put("ZES", "Suzuki");

        // TM (1 codes)
        WMI_MAP.put("ZDT", "TM");

        // TVR (1 codes)
        WMI_MAP.put("STA", "TVR");

        // TVS (3 codes)
        WMI_MAP.put("ME1", "TVS");
        WMI_MAP.put("ME3", "TVS");
        WMI_MAP.put("ME4", "TVS");

        // Talbot (1 codes)
        WMI_MAP.put("VFG", "Talbot");

        // Tank (1 codes)
        WMI_MAP.put("LZN", "Tank");

        // Tata (2 codes)
        WMI_MAP.put("MAT", "Tata");
        WMI_MAP.put("MCT", "Tata");

        // Tesla (3 codes)
        WMI_MAP.put("5YJ", "Tesla");
        WMI_MAP.put("LR1", "Tesla");
        WMI_MAP.put("LRW", "Tesla");

        // Toyota (71 codes)
        WMI_MAP.put("2T1", "Toyota");
        WMI_MAP.put("2T2", "Toyota");
        WMI_MAP.put("2T3", "Toyota");
        WMI_MAP.put("3TM", "Toyota");
        WMI_MAP.put("3TY", "Toyota");
        WMI_MAP.put("4T1", "Toyota");
        WMI_MAP.put("4T2", "Toyota");
        WMI_MAP.put("4T3", "Toyota");
        WMI_MAP.put("4T4", "Toyota");
        WMI_MAP.put("4TA", "Toyota");
        WMI_MAP.put("4TB", "Toyota");
        WMI_MAP.put("4TC", "Toyota");
        WMI_MAP.put("4TD", "Toyota");
        WMI_MAP.put("4TE", "Toyota");
        WMI_MAP.put("4TF", "Toyota");
        WMI_MAP.put("5T2", "Toyota");
        WMI_MAP.put("5TB", "Toyota");
        WMI_MAP.put("5TC", "Toyota");
        WMI_MAP.put("5TD", "Toyota");
        WMI_MAP.put("5TE", "Toyota");
        WMI_MAP.put("5TF", "Toyota");
        WMI_MAP.put("5TG", "Toyota");
        WMI_MAP.put("5TH", "Toyota");
        WMI_MAP.put("5TJ", "Toyota");
        WMI_MAP.put("5TK", "Toyota");
        WMI_MAP.put("5TL", "Toyota");
        WMI_MAP.put("5TM", "Toyota");
        WMI_MAP.put("5TN", "Toyota");
        WMI_MAP.put("5TP", "Toyota");
        WMI_MAP.put("5TT", "Toyota");
        WMI_MAP.put("5TU", "Toyota");
        WMI_MAP.put("5TY", "Toyota");
        WMI_MAP.put("5TZ", "Toyota");
        WMI_MAP.put("5YF", "Toyota");
        WMI_MAP.put("5YM", "Toyota");
        WMI_MAP.put("6T1", "Toyota");
        WMI_MAP.put("93R", "Toyota");
        WMI_MAP.put("9BR", "Toyota");
        WMI_MAP.put("JT1", "Toyota");
        WMI_MAP.put("JT2", "Toyota");
        WMI_MAP.put("JT3", "Toyota");
        WMI_MAP.put("JT4", "Toyota");
        WMI_MAP.put("JT5", "Toyota");
        WMI_MAP.put("JT6", "Toyota");
        WMI_MAP.put("JT7", "Toyota");
        WMI_MAP.put("JTA", "Toyota");
        WMI_MAP.put("JTB", "Toyota");
        WMI_MAP.put("JTC", "Toyota");
        WMI_MAP.put("JTD", "Toyota");
        WMI_MAP.put("JTE", "Toyota");
        WMI_MAP.put("JTF", "Toyota");
        WMI_MAP.put("JTG", "Toyota");
        WMI_MAP.put("JTK", "Toyota");
        WMI_MAP.put("JTL", "Toyota");
        WMI_MAP.put("JTM", "Toyota");
        WMI_MAP.put("JTN", "Toyota");
        WMI_MAP.put("JTR", "Toyota");
        WMI_MAP.put("JTS", "Toyota");
        WMI_MAP.put("JTT", "Toyota");
        WMI_MAP.put("JTU", "Toyota");
        WMI_MAP.put("JTV", "Toyota");
        WMI_MAP.put("JTW", "Toyota");
        WMI_MAP.put("JTX", "Toyota");
        WMI_MAP.put("JTY", "Toyota");
        WMI_MAP.put("JTZ", "Toyota");
        WMI_MAP.put("MBJ", "Toyota");
        WMI_MAP.put("SB1", "Toyota");
        WMI_MAP.put("SJH", "Toyota");
        WMI_MAP.put("VNK", "Toyota");
        WMI_MAP.put("X8A", "Toyota");
        WMI_MAP.put("XXU", "Toyota");

        // Trailer (1 codes)
        WMI_MAP.put("6T9", "Trailer");

        // Triumph (3 codes)
        WMI_MAP.put("SAT", "Triumph");
        WMI_MAP.put("SMT", "Triumph");
        WMI_MAP.put("STJ", "Triumph");

        // UAZ (2 codes)
        WMI_MAP.put("XD2", "UAZ");
        WMI_MAP.put("XTT", "UAZ");

        // Vauxhall (1 codes)
        WMI_MAP.put("SKF", "Vauxhall");

        // Venturi (1 codes)
        WMI_MAP.put("VFX", "Venturi");

        // Vespa (2 codes)
        WMI_MAP.put("MEG", "Vespa");
        WMI_MAP.put("ZDV", "Vespa");

        // Volkswagen (63 codes)
        WMI_MAP.put("1V1", "Volkswagen");
        WMI_MAP.put("1V2", "Volkswagen");
        WMI_MAP.put("1VW", "Volkswagen");
        WMI_MAP.put("3VV", "Volkswagen");
        WMI_MAP.put("3VW", "Volkswagen");
        WMI_MAP.put("9BW", "Volkswagen");
        WMI_MAP.put("LSV", "Volkswagen");
        WMI_MAP.put("LVW", "Volkswagen");
        WMI_MAP.put("MCW", "Volkswagen");
        WMI_MAP.put("MEX", "Volkswagen");
        WMI_MAP.put("SVW", "Volkswagen");
        WMI_MAP.put("VSP", "Volkswagen");
        WMI_MAP.put("VSV", "Volkswagen");
        WMI_MAP.put("VUC", "Volkswagen");
        WMI_MAP.put("VUD", "Volkswagen");
        WMI_MAP.put("VUE", "Volkswagen");
        WMI_MAP.put("VUG", "Volkswagen");
        WMI_MAP.put("VUH", "Volkswagen");
        WMI_MAP.put("VUJ", "Volkswagen");
        WMI_MAP.put("VUK", "Volkswagen");
        WMI_MAP.put("VUL", "Volkswagen");
        WMI_MAP.put("VUP", "Volkswagen");
        WMI_MAP.put("WR1", "Volkswagen");
        WMI_MAP.put("WTF", "Volkswagen");
        WMI_MAP.put("WV1", "Volkswagen");
        WMI_MAP.put("WV2", "Volkswagen");
        WMI_MAP.put("WV3", "Volkswagen");
        WMI_MAP.put("WV4", "Volkswagen");
        WMI_MAP.put("WV5", "Volkswagen");
        WMI_MAP.put("WV6", "Volkswagen");
        WMI_MAP.put("WV7", "Volkswagen");
        WMI_MAP.put("WV8", "Volkswagen");
        WMI_MAP.put("WV9", "Volkswagen");
        WMI_MAP.put("WVA", "Volkswagen");
        WMI_MAP.put("WVB", "Volkswagen");
        WMI_MAP.put("WVC", "Volkswagen");
        WMI_MAP.put("WVD", "Volkswagen");
        WMI_MAP.put("WVE", "Volkswagen");
        WMI_MAP.put("WVF", "Volkswagen");
        WMI_MAP.put("WVG", "Volkswagen");
        WMI_MAP.put("WVH", "Volkswagen");
        WMI_MAP.put("WVJ", "Volkswagen");
        WMI_MAP.put("WVK", "Volkswagen");
        WMI_MAP.put("WVL", "Volkswagen");
        WMI_MAP.put("WVM", "Volkswagen");
        WMI_MAP.put("WVN", "Volkswagen");
        WMI_MAP.put("WVP", "Volkswagen");
        WMI_MAP.put("WVR", "Volkswagen");
        WMI_MAP.put("WVS", "Volkswagen");
        WMI_MAP.put("WVT", "Volkswagen");
        WMI_MAP.put("WVU", "Volkswagen");
        WMI_MAP.put("WVV", "Volkswagen");
        WMI_MAP.put("WVW", "Volkswagen");
        WMI_MAP.put("WVX", "Volkswagen");
        WMI_MAP.put("WVY", "Volkswagen");
        WMI_MAP.put("WVZ", "Volkswagen");
        WMI_MAP.put("WXP", "Volkswagen");
        WMI_MAP.put("WXX", "Volkswagen");
        WMI_MAP.put("WZZ", "Volkswagen");
        WMI_MAP.put("X8X", "Volkswagen");
        WMI_MAP.put("XW8", "Volkswagen");
        WMI_MAP.put("XWB", "Volkswagen");
        WMI_MAP.put("YBW", "Volkswagen");

        // Volvo (97 codes)
        WMI_MAP.put("4V1", "Volvo");
        WMI_MAP.put("4V2", "Volvo");
        WMI_MAP.put("4V3", "Volvo");
        WMI_MAP.put("4V4", "Volvo");
        WMI_MAP.put("4V5", "Volvo");
        WMI_MAP.put("4V6", "Volvo");
        WMI_MAP.put("4VL", "Volvo");
        WMI_MAP.put("4VM", "Volvo");
        WMI_MAP.put("JVB", "Volvo");
        WMI_MAP.put("LV2", "Volvo");
        WMI_MAP.put("LV3", "Volvo");
        WMI_MAP.put("LV4", "Volvo");
        WMI_MAP.put("LV5", "Volvo");
        WMI_MAP.put("LV6", "Volvo");
        WMI_MAP.put("LV7", "Volvo");
        WMI_MAP.put("LV8", "Volvo");
        WMI_MAP.put("LV9", "Volvo");
        WMI_MAP.put("LVA", "Volvo");
        WMI_MAP.put("LVB", "Volvo");
        WMI_MAP.put("LVC", "Volvo");
        WMI_MAP.put("LVD", "Volvo");
        WMI_MAP.put("LVE", "Volvo");
        WMI_MAP.put("LVF", "Volvo");
        WMI_MAP.put("LVJ", "Volvo");
        WMI_MAP.put("LVK", "Volvo");
        WMI_MAP.put("LVL", "Volvo");
        WMI_MAP.put("LVM", "Volvo");
        WMI_MAP.put("LVN", "Volvo");
        WMI_MAP.put("LVP", "Volvo");
        WMI_MAP.put("LVT", "Volvo");
        WMI_MAP.put("LVU", "Volvo");
        WMI_MAP.put("LVX", "Volvo");
        WMI_MAP.put("LVY", "Volvo");
        WMI_MAP.put("LVZ", "Volvo");
        WMI_MAP.put("MC1", "Volvo");
        WMI_MAP.put("MC2", "Volvo");
        WMI_MAP.put("MCU", "Volvo");
        WMI_MAP.put("SKV", "Volvo");
        WMI_MAP.put("YB1", "Volvo");
        WMI_MAP.put("YB2", "Volvo");
        WMI_MAP.put("YB3", "Volvo");
        WMI_MAP.put("YLB", "Volvo");
        WMI_MAP.put("YV1", "Volvo");
        WMI_MAP.put("YV2", "Volvo");
        WMI_MAP.put("YV3", "Volvo");
        WMI_MAP.put("YV4", "Volvo");
        WMI_MAP.put("YV5", "Volvo");
        WMI_MAP.put("YV6", "Volvo");
        WMI_MAP.put("YV7", "Volvo");
        WMI_MAP.put("YV8", "Volvo");
        WMI_MAP.put("YV9", "Volvo");
        WMI_MAP.put("YVA", "Volvo");
        WMI_MAP.put("YVB", "Volvo");
        WMI_MAP.put("YVC", "Volvo");
        WMI_MAP.put("YVD", "Volvo");
        WMI_MAP.put("YVE", "Volvo");
        WMI_MAP.put("YVF", "Volvo");
        WMI_MAP.put("YVG", "Volvo");
        WMI_MAP.put("YVH", "Volvo");
        WMI_MAP.put("YVJ", "Volvo");
        WMI_MAP.put("YVK", "Volvo");
        WMI_MAP.put("YVL", "Volvo");
        WMI_MAP.put("YVM", "Volvo");
        WMI_MAP.put("YVN", "Volvo");
        WMI_MAP.put("YVP", "Volvo");
        WMI_MAP.put("YVR", "Volvo");
        WMI_MAP.put("YVS", "Volvo");
        WMI_MAP.put("YVT", "Volvo");
        WMI_MAP.put("YVU", "Volvo");
        WMI_MAP.put("YVV", "Volvo");
        WMI_MAP.put("YVW", "Volvo");
        WMI_MAP.put("YVX", "Volvo");
        WMI_MAP.put("YVY", "Volvo");
        WMI_MAP.put("YVZ", "Volvo");
        WMI_MAP.put("YWA", "Volvo");
        WMI_MAP.put("YWB", "Volvo");
        WMI_MAP.put("YWC", "Volvo");
        WMI_MAP.put("YWD", "Volvo");
        WMI_MAP.put("YWE", "Volvo");
        WMI_MAP.put("YWF", "Volvo");
        WMI_MAP.put("YWG", "Volvo");
        WMI_MAP.put("YWH", "Volvo");
        WMI_MAP.put("YWJ", "Volvo");
        WMI_MAP.put("YWK", "Volvo");
        WMI_MAP.put("YWL", "Volvo");
        WMI_MAP.put("YWM", "Volvo");
        WMI_MAP.put("YWN", "Volvo");
        WMI_MAP.put("YWP", "Volvo");
        WMI_MAP.put("YWR", "Volvo");
        WMI_MAP.put("YWS", "Volvo");
        WMI_MAP.put("YWT", "Volvo");
        WMI_MAP.put("YWU", "Volvo");
        WMI_MAP.put("YWV", "Volvo");
        WMI_MAP.put("YWW", "Volvo");
        WMI_MAP.put("YWX", "Volvo");
        WMI_MAP.put("YWY", "Volvo");
        WMI_MAP.put("YWZ", "Volvo");

        // Westfield (1 codes)
        WMI_MAP.put("SSA", "Westfield");

        // Witte (1 codes)
        WMI_MAP.put("WKE", "Witte");

        // Xpeng (17 codes)
        WMI_MAP.put("LXG", "Xpeng");
        WMI_MAP.put("LXH", "Xpeng");
        WMI_MAP.put("LXJ", "Xpeng");
        WMI_MAP.put("LXK", "Xpeng");
        WMI_MAP.put("LXL", "Xpeng");
        WMI_MAP.put("LXM", "Xpeng");
        WMI_MAP.put("LXN", "Xpeng");
        WMI_MAP.put("LXP", "Xpeng");
        WMI_MAP.put("LXR", "Xpeng");
        WMI_MAP.put("LXS", "Xpeng");
        WMI_MAP.put("LXT", "Xpeng");
        WMI_MAP.put("LXU", "Xpeng");
        WMI_MAP.put("LXV", "Xpeng");
        WMI_MAP.put("LXW", "Xpeng");
        WMI_MAP.put("LXX", "Xpeng");
        WMI_MAP.put("LXY", "Xpeng");
        WMI_MAP.put("LXZ", "Xpeng");

        // Yamaha (20 codes)
        WMI_MAP.put("JYA", "Yamaha");
        WMI_MAP.put("JYB", "Yamaha");
        WMI_MAP.put("JYC", "Yamaha");
        WMI_MAP.put("JYD", "Yamaha");
        WMI_MAP.put("JYE", "Yamaha");
        WMI_MAP.put("JYF", "Yamaha");
        WMI_MAP.put("JYH", "Yamaha");
        WMI_MAP.put("JYJ", "Yamaha");
        WMI_MAP.put("MDZ", "Yamaha");
        WMI_MAP.put("VG6", "Yamaha");
        WMI_MAP.put("VG7", "Yamaha");
        WMI_MAP.put("VGD", "Yamaha");
        WMI_MAP.put("VGF", "Yamaha");
        WMI_MAP.put("VGG", "Yamaha");
        WMI_MAP.put("VGL", "Yamaha");
        WMI_MAP.put("YC1", "Yamaha");
        WMI_MAP.put("YES", "Yamaha");
        WMI_MAP.put("ZD0", "Yamaha");
        WMI_MAP.put("ZDY", "Yamaha");
        WMI_MAP.put("ZJY", "Yamaha");

        // Yutong (1 codes)
        WMI_MAP.put("LZY", "Yutong");

        // ZAZ (1 codes)
        WMI_MAP.put("XUA", "ZAZ");

        // Zhongtai (1 codes)
        WMI_MAP.put("LDY", "Zhongtai");

    }

    /**
     * Get manufacturer from WMI code
     */
    public String getManufacturer(String wmi) {
        if (wmi == null || wmi.length() < 2) {
            return null;
        }

        // Try exact match first
        String manufacturer = WMI_MAP.get(wmi.toUpperCase());
        if (manufacturer != null) {
            return manufacturer;
        }

        // Try with first 2 characters
        if (wmi.length() >= 2) {
            return WMI_MAP.get(wmi.substring(0, 2).toUpperCase());
        }

        return null;
    }

    /**
     * Get manufacturer by prefix (for fallback)
     */
    public String getManufacturerByPrefix(String prefix) {
        if (prefix == null || prefix.length() < 2) {
            return null;
        }

        prefix = prefix.toUpperCase();

        // Check 2-character manufacturers
        switch (prefix) {
            case "1F": return "Ford";
            case "1G": return "General Motors";
            case "1H": return "Honda";
            case "1J": return "Jeep";
            case "1L": return "Lincoln";
            case "1M": return "Mercury/Mack";
            case "1N": return "Nissan";
            case "1V": return "Volkswagen";
            case "1Y": return "Mazda";
            case "2F": return "Ford Canada";
            case "2G": return "General Motors Canada";
            case "2H": return "Honda Canada";
            case "2T": return "Toyota Canada";
            case "3F": return "Ford Mexico";
            case "3G": return "General Motors Mexico";
            case "3H": return "Honda Mexico";
            case "3N": return "Nissan Mexico";
            case "3V": return "Volkswagen Mexico";
            case "4F": return "Mazda USA";
            case "4J": return "Mercedes-Benz USA";
            case "4M": return "Mercury USA";
            case "4S": return "Subaru USA";
            case "4T": return "Toyota USA";
            case "4U": return "BMW USA";
            case "4V": return "Volvo USA";
            case "5F": return "Honda USA";
            case "5L": return "Lincoln USA";
            case "5N": return "Nissan/Infiniti USA";
            case "5T": return "Toyota USA";
            case "5U": return "BMW USA";
            case "5X": return "Hyundai/Kia USA";
            case "5Y": return "Tesla/Toyota USA";
            case "JA": return "Isuzu";
            case "JF": return "Subaru";
            case "JH": return "Honda";
            case "JM": return "Mazda";
            case "JN": return "Nissan";
            case "JT": return "Toyota";
            case "JY": return "Yamaha";
            case "KL": return "Daewoo/GM Korea";
            case "KM": return "Hyundai";
            case "KN": return "Kia";
            case "WA": return "Audi";
            case "WB": return "BMW";
            case "WD": return "Mercedes-Benz";
            case "WF": return "BMW Motorrad";
            case "WM": return "MINI";
            case "WP": return "Porsche";
            case "WU": return "BMW";
            case "WV": return "Volkswagen";
            case "W0": return "Volkswagen Commercial";
            case "ZA": return "Alfa Romeo";
            case "ZC": return "Chrysler Europe";
            case "ZF": return "Ferrari";
            case "ZH": return "Honda Europe";
            case "ZL": return "Lamborghini";
            case "SA": return "Land Rover/Rover";
            case "SB": return "Bentley";
            case "SC": return "Lotus";
            case "SH": return "Honda UK";
            case "SJ": return "Jaguar";
            case "TR": return "Triumph";
            case "TM": return "Toyota UK";
            case "TN": return "Nissan UK";
            case "TY": return "Toyota Europe";
            case "UU": return "Dacia Romania";
            case "VA": return "Volvo Austria";
            case "VF": return "Renault";
            case "VG": return "Peugeot";
            case "VN": return "Citroën";
            case "VR": return "DS Automobiles";
            case "VS": return "SEAT";
            case "VV": return "Volkswagen Spain";
            case "VX": return "Opel/Vauxhall";
            case "XL": return "Lada";
            case "XP": return "Porsche (Russia)";
            case "XT": return "UAZ";
            case "XU": return "AvtoVAZ";
            case "XW": return "GAZ";
            case "YE": return "Mazda Europe";
            case "YH": return "Honda Belgium";
            case "YK": return "Toyota Belgium";
            case "YS": return "Scania";
            case "YT": return "Toyota Europe";
            case "YV": return "Volvo";
            case "ZD": return "Fiat/Dodge Europe";
            case "ZG": return "Maserati";
        }

        return null;
    }

    /**
     * Get region from VIN's first character
     */
    public String getRegion(char firstChar) {
        String region = REGION_MAP.get(firstChar);
        if (region != null) {
            return region;
        }

        // Group regions
        if (firstChar >= '1' && firstChar <= '5') {
            return "North America";
        } else if (firstChar >= '6' && firstChar <= '7') {
            return "Oceania";
        } else if (firstChar >= '8' && firstChar <= '9') {
            return "South America";
        } else if (firstChar >= 'A' && firstChar <= 'H') {
            return "Africa";
        } else if (firstChar >= 'J' && firstChar <= 'R') {
            return "Asia";
        } else if (firstChar >= 'S' && firstChar <= 'Z') {
            return "Europe";
        }

        return "Unknown";
    }
}
