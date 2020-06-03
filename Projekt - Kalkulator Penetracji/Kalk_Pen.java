import java.util.*;
import java.lang.Math;
public class Kalk_Pen{
    public static void main(String[] args){
        int wybor = 0;
        Sowiecka_stal cel = new Sowiecka_stal();
        Scanner skan = new Scanner(System.in);
        System.out.println("Witam w kalkulatorze penetracji");
        System.out.println("Polecam zapoznanie się z dolaczonym plikem tekstowym w celu zrozumienia kalkulacji");
        System.out.println("Jaki Pocisk chcesz obliczyc");
        while(true){
            System.out.println("1.AP 2.APDSFS 3.Wyjdz");
            try{
                wybor = skan.nextInt();
            }
            catch(InputMismatchException exception){
                System.out.println("Prosze podac liczbe calkowita");
                skan.next();//Zeby nie powstawal infinite loop po bledzie
            }
            switch(wybor){
                case 1:
                    double temp_ap;
                    System.out.println("Podaj Parametry Pocisku");
                    Pocisk ten_pocisk = new Pocisk();
                    try{ 
                        System.out.println("Podaj srednice pocisku w mm");
                        while(true){
                            temp_ap = skan.nextDouble();
                            if(temp_ap < 2){
                                System.out.println("Podaj wartosc wieksza niz 2");
                            }
                            else {
                                ten_pocisk.srednica=temp_ap;
                                break;
                            }
                        }
                        while(true){
                            System.out.println("Podaj dlugosc pocisku w mm");
                            ten_pocisk.dlugosc=skan.nextDouble();
                            if(ten_pocisk.dlugosc<ten_pocisk.srednica){
                                System.out.println("Srednica pocisku nie moze byc wieksza niz jego dlugosc");
                            }
                            else{
                                break;
                            }
                        }
                        while(true){
                            System.out.println("Podaj predkosc uderzenia w m/s");
                            temp_ap = skan.nextDouble();
                            if(temp_ap<=0){
                                System.out.println("podaj wartosc dodatnia");
                            }
                            else{
                                ten_pocisk.predkosc=temp_ap;
                                break;
                            }
                        }
                        ten_pocisk.masa=Pocisk.licz_masa(ten_pocisk);
                        Pocisk.pen_ap_krupp(ten_pocisk.predkosc,ten_pocisk.masa,cel.wytrzymalosc,ten_pocisk.srednica);
                    }
                    catch(InputMismatchException exception){
                        System.out.println("Podaj poprawne liczby");
                        skan.next();//Żeby nie powstawal infinite loop po bledzie
                    }
                    break;
                case 2:
                    try{
                        APFSDS long_rod = new APFSDS();
                        double temp;
                        System.out.println("Podaj srednice penetratora w mm");
                        while(true){
                            temp = skan.nextDouble();
                            if(temp < 1){
                                System.out.println("Podaj wartość wieksza niż jeden");
                            }
                            else{
                                long_rod.srednica=temp;
                                break;
                            }
                        }
                        System.out.println("Wprowadz dlugosc pocisku");
                        System.out.println("Jezeli wartosc bedzie rowna zero bedzie wprowadzone 32 krotnosc srednicy");
                        while(true){
                            temp = skan.nextDouble();
                            if(temp < long_rod.srednica && temp != 0){
                                System.out.println("Dlugosc musi byc wieksza niz srednica");
                            }
                            else if(temp == 0){
                                long_rod.dlugosc = 32*long_rod.srednica;
                                break;
                            }
                            else{
                                long_rod.dlugosc=temp;
                                break;
                            }
                        }
                        System.out.println("Wprowadz dlugosc czubka");
                        while(true){
                            temp = skan.nextDouble();
                            if(temp > long_rod.dlugosc){
                                System.out.println("Dlugosc czubka nie może byc wieksza niz dlugosc penetratora");
                            }
                            else if(temp==0){
                                System.out.println("Penetrator bedzie walcem");
                                long_rod.czubek_dlugosc = temp;
                                break;
                            }
                            else if(temp < 0){
                                System.out.println("Dlugosc nie moze byc ujemna");
                            }
                            else{
                                long_rod.czubek_dlugosc = temp;
                                break;
                            }
                        }
                        if(long_rod.czubek_dlugosc == 0){
                            long_rod.dlugosc_robocza = long_rod.dlugosc;
                        }
                        else{
                            System.out.println("Podaj srednice czubka");
                            while(true){
                                temp = skan.nextDouble();
                                if (temp <=0){
                                    System.out.println("Srednica czubka nie moze wynosic zero lub mniej");
                                }
                                else if(temp > long_rod.srednica){
                                       System.out.println("czubek nie moze byc wiekszy niz srednica calego penetratora");
                                }
                                else{
                                    long_rod.czubek=temp;
                                     break;
                                }
                            }
                            long_rod.dlugosc_robocza=APFSDS.kalk_dr(long_rod);
                        }
                        System.out.println("wprowadz predkosc uderzenia w m/s wieksza niz 935m/s");
                        while(true){
                            temp = skan.nextDouble();
                            if(temp < 935){
                                System.out.println("wprowadz predkosc wieksza niz 935m/s");
                            }
                            else{
                                long_rod.predkosc = temp/1000;
                                break;
                            }
                        }
                        System.out.println("Wprowadz watrosc twardosci celu w skali 500>x>150 lub 0 zeby zastosowac wartosc domyslna");
                        System.out.println("Domyslna wartosc to 240");
                        while(true){
                            temp = skan.nextDouble();
                            if((temp>500 || temp<150) & temp != 0){
                                System.out.println("Wrpowadz wartosc z zakresu lub 0");
                            }
                            else if(temp == 0){
                                cel.twardosc=240;
                                break;
                            }
                            else{
                                break;
                            }
                        }
                        APFSDS.kalk_apfs(long_rod, cel);
                    }
                    catch(InputMismatchException exception){
                        System.out.println("Podaj poprawne liczby");
                        skan.next();//Zeby nie powstawal infinite loop po bledzie
                    }
                    break;
                case 3:
                    System.exit(wybor);
                    break;
                default:
                    System.out.println("Prosze podać poprawną liczbe");
                    wybor = 0;
                    break;
            }
        }
    }
}
class Pocisk{
    double masa;
    double srednica;
    double dlugosc;
    double predkosc;
    double gestosc = 0.000008; //Gestosc stali nierdzewnej w kg/mm3 z ktorej wykonanej byly wczesne pociski AP 
    public static void pen_ap_krupp(double V,double M, double K, double D){ // V - Predkosc, M - Masa,K -wytrzymalosc pancerza, D- Srednica pocisku
        double penetracja;
        penetracja = 100*((V*(Math.sqrt(M)))/(K*(Math.sqrt(D)))); // Wzor Kruppa dla pociskow ap ktory daje wynik w decymetrach. 100* zeby byly mm
        System.out.println(penetracja+"mm");
    }
    public static double licz_masa(Pocisk a){
        double promien = a.srednica/2;
        double objetosc = (promien*promien)*3.14*a.srednica;
        double mass = (0.9*objetosc)*a.gestosc;
        return mass;
    }
}
class APFSDS extends Pocisk{
    double czubek_dlugosc; //Frustrum to bryła scięta. Jest to czubek pocisku. Dlugosc
    double czubek; //Srednica czubka.
    private double pen_gestosc = 17200; //gestosc wolframu
    double dlugosc_robocza;

    public static double kalk_dr(APFSDS a){    //Dlugosc "robocza" pocisku. Nie caly pocisk przy kontakcie przekazuje sile
        a.dlugosc_robocza=a.dlugosc-(0.7*a.czubek_dlugosc);
        return a.dlugosc_robocza;
    }
    public double get_gestosc(){
        return pen_gestosc;
    }
    public static void kalk_apfs(APFSDS ten, Sowiecka_stal c1){ //Wzor sklada sie z mnozenia wiec dla swojej czytelnosci podzielilem go na czesci
        double s = ((138+(-0.1*c1.twardosc))*c1.twardosc)/ten.get_gestosc();
        double segment1 = Math.sqrt(ten.get_gestosc()/c1.get_gestosc_cel());
        double segment2 = Math.exp((-s)/(ten.predkosc*ten.predkosc));
        double wynik;
        wynik = 0.921*(1/(Math.tanh(0.283+(0.0656*(ten.dlugosc_robocza/ten.srednica)))))*segment1*segment2;
        wynik = ten.dlugosc_robocza*wynik;
        System.out.println(wynik +"mm");
    }
}
class Sowiecka_stal{
    public double wytrzymalosc = 240; //Stala wartosc wytzymalosci stali uzywana w kalkulacji kruppa przez sowietow
    private double gestosc_cel = 7850; //gestosc stali sowieckiej(chyba), 
    public double twardosc = 240; //Twardosc stali sowieckiej w skali Brinella.
    public double get_gestosc_cel(){
        return gestosc_cel;
    }
}