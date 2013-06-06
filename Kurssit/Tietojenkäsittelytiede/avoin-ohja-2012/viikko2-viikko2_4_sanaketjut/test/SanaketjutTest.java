import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

import java.util.*;


public class SanaketjutTest {
	private Class luokka;
	private Constructor konstruktori;
        private Class tekoalynLuokka;
        private Constructor tekoalynKonstruktori;

	@Before
	public void setUp() {
		try {
			luokka = ReflectionUtils.findClass("Tarkistaja");
		} catch (AssertionError e) {
			fail("Olethan tehnyt luokan Tarkistaja?");
		} catch (Throwable t) {
			fail("Outo virhe.");
		}
		
		try {
			konstruktori = ReflectionUtils.requireConstructor(luokka, String.class);
		} catch (AssertionError e) {
			fail("Luokan Tarkistaja konstruktorin tulee saada parametriksi yksi merkkijono");
		}
	}    

	private Method haeMetodi(String metodinNimi) {
		Method metodi = null;
		try {
			metodi = ReflectionUtils.requireMethod(luokka, metodinNimi);
		} catch (Throwable t) {
			testaaEtteiParametrillinen(metodinNimi);
			fail("Luokalla tulee olla metodi \"" + metodinNimi + "\". Ethän anna sille mitään parametrejä.");
		}
		return metodi;
	}
	
	private Method haeMetodi(Class palautusarvo, String palautusarvonNimi, String metodinNimi) {
		Method metodi = haeMetodi(metodinNimi);
		try {
			metodi = ReflectionUtils.requireMethod(luokka, palautusarvo, metodinNimi);
		} catch (Throwable t) {
			fail("Luokan metodin " + metodinNimi + " tulee palauttaa " + palautusarvonNimi);
		}
		
		return metodi;
	}
	
	private void testaaEtteiParametrillinen(String metodinNimi) {
		try {
			ReflectionUtils.requireMethod(luokka, metodinNimi, int.class);
		} catch (Throwable t) {
			return;
		}
		fail("Luokan metodin " + metodinNimi + " ei tullut ottaa parametrejä.");
	}
	
	private void testaaEtteiParametriton(String metodinNimi, String parametrinNimi) {
		try {
			ReflectionUtils.requireMethod(luokka, metodinNimi);
		} catch (Throwable t) {
			return;
		}
		fail("Luokan metodin " + metodinNimi + " tulisi pyytää parametri: " + parametrinNimi);
	}
	
	private Method haeMetodi(String metodinNimi, Class parametri, String parametrinNimi) {
		Method metodi = null;
		try {
			metodi = ReflectionUtils.requireMethod(luokka, metodinNimi, parametri);
		} catch (Throwable t) {
			testaaEtteiParametriton(metodinNimi, parametrinNimi);
			fail("Luokalla tulee olla metodi " + metodinNimi + " ja sen tulee pyytää yksi " + parametrinNimi + "-tyyppinen parametri.");
		}
		
		return metodi;
	}
	
	private Method haeMetodi(Class palautusarvo, String palautusarvonNimi, String metodinNimi, Class parametri, String parametrinNimi) {
		Method metodi = haeMetodi(metodinNimi, parametri, parametrinNimi);
		
		try {
			metodi = ReflectionUtils.requireMethod(luokka, palautusarvo, metodinNimi, parametri);
		} catch (Throwable t) {
			fail("Luokan metodin " + metodinNimi + " tulee palauttaa " + palautusarvonNimi);
		}
		
		return metodi;
	}
        
	@Test
	@Points("2.4.1")
	public void lisaaSanaToimii() {
		testaaLisaaSana(new String[] {"ababab", "apina"}, new int[] {1, 0});
		testaaLisaaSana(new String[] {"aybabtu", "selleri"}, new int[] {1, 0});
                
                testaaLisaaSana(new String[] {"apina", "apina"}, new int[] {0, 2});
                testaaLisaaSana(new String[] {"talo", "valo", "talo"}, new int[] {0, 0, 2});
                testaaLisaaSana(new String[] {"talo", "ababab", "valo", "talo"}, new int[] {0, 1, 0, 2});
                testaaLisaaSana(new String[] {"zzz", "zzz", "zzz"}, new int[] {1, 1, 1});
	}
        
	@Test
	@Points("2.4.2")
	public void lisaaSanaToimiiTaysin() {
            
                testaaLisaaSana(new String[] {"talo", "valo", "vala", "vana", "kana", "kani"},
                                           new int[] {0, 0, 0, 0, 0, 0});
                testaaLisaaSana(new String[] {"kasa", "kassa", "kassi", "kasti", "lasti", "lasi"},
                                           new int[] {0, 0, 0, 0, 0, 0});
                testaaLisaaSana(new String[] {"aamu", "haamu"}, new int[] {0, 0});
                testaaLisaaSana(new String[] {"haamu", "aamu"}, new int[] {0, 0});
                testaaLisaaSana(new String[] {"kas", "kasa"}, new int[] {0, 0});
                testaaLisaaSana(new String[] {"kasa", "kas"}, new int[] {0, 0});
                testaaLisaaSana(new String[] {"peli", "eli", "elo", "eno", "meno", "mono",
                                           "moni", "poni", "koni", "kani", "kana",
                                           "akana", "lakana"},
                             new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                
                testaaLisaaSana(new String[] {"kasa", "sana"}, new int[] {0, 3});
                testaaLisaaSana(new String[] {"sana", "kasa"}, new int[] {0, 3});
                testaaLisaaSana(new String[] {"ala", "valas"}, new int[] {0, 3});
                testaaLisaaSana(new String[] {"valas", "ala"}, new int[] {0, 3});
                testaaLisaaSana(new String[] {"kana", "kinos"}, new int[] {0, 3});
                testaaLisaaSana(new String[] {"kinos", "kana"}, new int[] {0, 3});
                testaaLisaaSana(new String[] {"katu", "katos"}, new int[] {0, 3});
                testaaLisaaSana(new String[] {"katos", "katu"}, new int[] {0, 3});
                
                testaaLisaaSana(new String[] {"puu", "suu", "luu", "puu"},
                             new int[] {0, 0, 0, 2});
                testaaLisaaSana(new String[] {"puu", "suu", "luu", "suu"},
                             new int[] {0, 0, 0, 2});
                testaaLisaaSana(new String[] {"puu", "suu", "luu", "suu", "muu"},
                             new int[] {0, 0, 0, 2, 0});
                testaaLisaaSana(new String[] {"puu", "suu", "luu", "suu", "muu", "kuu", "puu"},
                             new int[] {0, 0, 0, 2, 0, 0, 2});
                
                testaaLisaaSana(new String[] {"zzz", "kala", "valo", "salo", "vala", "kala",
                                           "abc", "valo", "salo", "sana", "valo", "sali"},
                             new int[] {1, 0, 3, 3, 0, 2, 1, 0, 0, 3, 2, 0});
	}        

	@Test
	@Points("2.4.3")
	public void ketjunPituusToimii() {
		testaaKetjunPituus(new String[] {"puu", "kuu", "suu"}, 3);
		testaaKetjunPituus(new String[] {"puu", "abc", "suu"}, 2);
		testaaKetjunPituus(new String[] {"puu", "kuu", "puu", "kuu", "suu"}, 3);
	}
                
	@Test
	@Points("2.4.3")
	public void haeKetjuToimii() {
                ArrayList<String> a = new ArrayList<String>();
                a.add("puu");
                a.add("kuu");
                a.add("suu");
                ArrayList<String> b = new ArrayList<String>();
                b.add("puu");
                b.add("suu");
                
                testaaHaeKetju(new String[] {"puu", "kuu", "suu"}, a);
		testaaHaeKetju(new String[] {"puu", "abc", "suu"}, b);
		testaaHaeKetju(new String[] {"puu", "kuu", "puu", "kuu", "suu"}, a);
	}        

	@Test
	@Points("2.4.3")
	public void haeSeuraavatToimii() {
                ArrayList<String> a = new ArrayList<String>(); 
                a.add("lantti");
                a.add("lantto");
                a.add("lunttu");
                a.add("planttu");
                a.add("vanttu");
                ArrayList<String> b = new ArrayList<String>();
                b.add("laitto");
                b.add("lantio");
                ArrayList<String> c = new ArrayList<String>();
                testaaHaeSeuraavat(new String[] {"lanttu"}, a);
                testaaHaeSeuraavat(new String[] {"lanttu", "lantti", "lantto"}, b);
                testaaHaeSeuraavat(new String[] {"ohjelmointi"}, c);
        }
        
	@Test
	@Points("2.4.4")
	public void valitseSanaToimii() {
                ArrayList<String> a = new ArrayList<String>(); 
                a.add("lantti");
                a.add("lantto");
                a.add("lunttu");
                a.add("planttu");
                a.add("vanttu");
                ArrayList<String> b = new ArrayList<String>();
                b.add("laitto");
                b.add("lantio");
                ArrayList<String> c = new ArrayList<String>();
                ArrayList<String> d = new ArrayList<String>();
                d.add("aallotar");
                testaaValitseSana(new String[] {"lanttu"}, a);
                testaaValitseSana(new String[] {"lanttu", "lantti", "lantto"}, b);
                testaaValitseSana(new String[] {"ohjelmointi"}, c);
                testaaValitseSana(new String[] {"aallota"}, d);
	}              
        
        
        private void testaaLisaaSana(String[] sanat, int[] tulokset) {
            Method metodi = haeMetodi(int.class, "kokonaisluku", "lisaaSana", String.class, "merkkijono");
            Object tarkistaja = new Object();
            try {
                tarkistaja = ReflectionUtils.invokeConstructor(konstruktori, "sanalista.txt");
            } catch (Throwable t) {
                fail("Virhe luokan Tarkistaja konstruktorissa");
            }
            String lista = "";
            for (int i = 0; i < sanat.length; i++) {
                int tulos = 0;
                try {
                    tulos = ReflectionUtils.invokeMethod(int.class, metodi, tarkistaja, sanat[i]);
                } catch (Throwable t) {
                    fail("Virhe luokan Tarkistaja metodin lisaaSana testaamisessa");
                }
                if (tulos != tulokset[i]) {
                    if (lista.equals("")) {
                        fail("Metodin lisaaSana tulisi palauttaa arvo " + tulokset[i] + " eikä " + tulos + ", kun lisätään sana " + sanat[i]);                        
                    } else {
                        fail("Metodin lisaaSana tulisi palauttaa arvo " + tulokset[i] + " eikä " + tulos + ", kun on lisätty sanat " + lista + " ja lisätään sana " + sanat[i]);
                    }
                }
                if (lista.equals("")) {
                    lista = sanat[i];
                } else {
                    lista = lista + "," + sanat[i];
                }
            }
        }
        
        private void testaaKetjunPituus(String[] sanat, int oikeaTulos) {
            Method metodi = haeMetodi(int.class, "kokonaisluku", "lisaaSana", String.class, "merkkijono");
            Method metodi2 = haeMetodi(int.class, "kokonaisluku", "ketjunPituus");
            Object tarkistaja = new Object();
            try {
                tarkistaja = ReflectionUtils.invokeConstructor(konstruktori, "sanalista.txt");
            } catch (Throwable t) {
                fail("Virhe luokan Tarkistaja konstruktorissa");
            }
            String lista = "";
            try {
                for (int i = 0; i < sanat.length; i++) {
                    ReflectionUtils.invokeMethod(int.class, metodi, tarkistaja, sanat[i]);
                    if (lista.equals("")) {
                        lista = lista + sanat[i];
                    } else {
                        lista = lista + "," + sanat[i];
                    }
                }
            } catch (Throwable t) {
                fail("Onhan luokassa Tarkistaja metodi lisaaSana?");
            }
            int tulos = 0;
            try {
                tulos = ReflectionUtils.invokeMethod(int.class, metodi2, tarkistaja);
            } catch (Throwable e) {
                fail("Onhan luokassa Tarkistaja metodi ketjunPituus?");
            }
            if (tulos != oikeaTulos) {
                fail("Kun ketjuun lisätään sanat " + lista + ", metodin ketjunPituus tulisi palauttaa " + oikeaTulos + " eikä " + tulos);
            }
        }
        
        private void testaaHaeKetju(String[] sanat, ArrayList<String> oikeaKetju) {
            Method metodi = haeMetodi(int.class, "kokonaisluku", "lisaaSana", String.class, "merkkijono");
            Method metodi2 = haeMetodi(ArrayList.class, "lista", "haeKetju");
            Object tarkistaja = new Object();
            try {
                tarkistaja = ReflectionUtils.invokeConstructor(konstruktori, "sanalista.txt");
            } catch (Throwable t) {
                fail("Virhe luokan Tarkistaja konstruktorissa");
            }
            String lista = "";
            try {
                for (int i = 0; i < sanat.length; i++) {
                    ReflectionUtils.invokeMethod(int.class, metodi, tarkistaja, sanat[i]);
                    if (lista.equals("")) {
                        lista = lista + sanat[i];
                    } else {
                        lista = lista + "," + sanat[i];
                    }
                }
            } catch (Throwable t) {
                fail("Onhan luokassa Tarkistaja metodi lisaaSana?");
            }
            ArrayList<String> ketju = new ArrayList<String>();
            try {
                ketju = ReflectionUtils.invokeMethod(ArrayList.class, metodi2, tarkistaja);
            } catch (Throwable e) {
                fail("Onhan luokassa Tarkistaja metodi haeKetju?");
            }
            if (!ketju.equals(oikeaKetju)) {
                fail("Kun ketjuun lisätään sanat " + lista + ", metodin haeKetju tulisi palauttaa " + oikeaKetju + " eikä " + ketju);
            }
        }             

        private void testaaHaeSeuraavat(String[] sanat, ArrayList<String> oikeaSeuraavat) {
            Method metodi = haeMetodi(int.class, "kokonaisluku", "lisaaSana", String.class, "merkkijono");
            Method metodi2 = haeMetodi(ArrayList.class, "lista", "haeSeuraavat");
            Object tarkistaja = new Object();
            try {
                tarkistaja = ReflectionUtils.invokeConstructor(konstruktori, "sanalista.txt");
            } catch (Throwable t) {
                fail("Virhe luokan Tarkistaja konstruktorissa");
            }
            String lista = "";
            try {
                for (int i = 0; i < sanat.length; i++) {
                    ReflectionUtils.invokeMethod(int.class, metodi, tarkistaja, sanat[i]);
                    if (lista.equals("")) {
                        lista = lista + sanat[i];
                    } else {
                        lista = lista + "," + sanat[i];
                    }
                }
            } catch (Throwable t) {
                fail("Onhan luokassa Tarkistaja metodi lisaaSana?");
            }
            ArrayList<String> seuraavat = new ArrayList<String>();
            try {
                seuraavat = ReflectionUtils.invokeMethod(ArrayList.class, metodi2, tarkistaja);
                Collections.sort(seuraavat);
            } catch (Throwable e) {
                fail("Onhan luokassa Tarkistaja metodi haeSeuraavat?");
            }
            if (!seuraavat.equals(oikeaSeuraavat)) {
                fail("Kun ketjuun lisätään sanat " + lista + ", metodin haeSeuraavat tulisi palauttaa " + oikeaSeuraavat + " eikä " + seuraavat);
            }
        }             

        private void testaaValitseSana(String[] sanat, ArrayList<String> mahdollisuudet) {
            Object tarkistaja = new Object();
            Object tekoaly = new Object();
            try {
                tarkistaja = ReflectionUtils.invokeConstructor(konstruktori, "sanalista.txt");
            } catch (Throwable t) {
                fail("Virhe luokan Tarkistaja konstruktorissa");
            }
            
            try {
                tekoalynLuokka = ReflectionUtils.findClass("Tekoaly");
            } catch (AssertionError e) {
                fail("Olethan tehnyt luokan Tekoaly?");
            } catch (Throwable t) {
                fail("Outo virhe.");
            }
		
            try {
                tekoalynKonstruktori = ReflectionUtils.requireConstructor(tekoalynLuokka, tarkistaja.getClass());
            } catch (AssertionError e) {
                fail("Luokan Tekoaly konstruktorin tulee saada parametriksi Tarkistaja-olio");
            }

            Method metodi = haeMetodi(int.class, "kokonaisluku", "lisaaSana", String.class, "merkkijono");
            Class vara = luokka;
            luokka = tekoalynLuokka;
            Method metodi2 = haeMetodi(String.class, "lista", "valitseSana");
            luokka = vara;
            
            try {
                tekoaly = ReflectionUtils.invokeConstructor(tekoalynKonstruktori, tarkistaja);
            } catch (Throwable t) {
                fail("Virhe luokan Tekoaly konstruktorissa");
            }
            String lista = "";
            try {
                for (int i = 0; i < sanat.length; i++) {
                    ReflectionUtils.invokeMethod(int.class, metodi, tarkistaja, sanat[i]);
                    if (lista.equals("")) {
                        lista = lista + sanat[i];
                    } else {
                        lista = lista + "," + sanat[i];
                    }
                }
            } catch (Throwable t) {
                fail("Onhan luokassa Tarkistaja metodi lisaaSana?");
            }
            HashMap<String, Integer> jakauma = new HashMap<String, Integer>();
            if (mahdollisuudet.size() == 0) {
                jakauma.put("", 0);
            } else {
                for (String valinta : mahdollisuudet) {
                    jakauma.put(valinta, 0);
                }
            }
            String valinta = "";
            for (int i = 0; i < 100; i++) {
                try {
                    valinta = ReflectionUtils.invokeMethod(String.class, metodi2, tekoaly);
                } catch (Throwable e) {
                    fail("Onhan luokassa Tarkistaja metodi haeSeuraavat?");
                }
                if (!jakauma.containsKey(valinta)) {
                    if (valinta.equals("")) {
                        fail("Tekoäly antaa tyhjän merkkijonon, kun sanaketjussa on valmiiksi " + lista);
                    } else {
                        fail("Tekoäly antaa virheellisen sanan " + valinta + ", kun sanaketjussa on valmiiksi " + lista);
                    }
                } else {
                    jakauma.put(valinta, jakauma.get(valinta) + 1);
                }
            }
            for (String sana : jakauma.keySet()) {
                if (jakauma.get(sana) < 100.0 / jakauma.size() / 2) {
                    fail("Tekoäly antaa liian harvoin sanaa " + sana + ", kun sanaketjussa on valmiiksi " + lista);
                }
            }
            
        }             
}
