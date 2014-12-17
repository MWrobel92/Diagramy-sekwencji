package widok;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import model.Diagram;
import javax.swing.JPanel;
import model.Komunikat;
import model.Obiekt;
import model.ObszarWydzielony;


/**
 * Komponent, na którym jest rysowany diagram. 
 * @author Michał Wróbel
 */
public class PanelDiagramu extends JPanel {
    
    private Diagram modelDiagramu;
    
    private int wysokoscNaglowka;
    private int wysokoscKratki;
    private int szerokoscKratki;
    
    private int wysokoscSiatki;
    private int szerokoscSiatki;
    
    private int wspolrzednaKonca;
    private int szerokoscKontrolki;
    
    int[] nadmiaroweSzerokosciObiektow;
    
    /**
    * Konstruktor
    * @param modelDiagramu Model, na podstawie którego ma być rysowany diagram. 
    */
    public PanelDiagramu(Diagram modelDiagramu) {        
        ladujPonownie(modelDiagramu);
    }
    
    /**
     * Ustawia wymiary pojedynczego elementu siatki w pikselach.
     * Bez wywołania tej metody domyślne rozmiery to 50 x 150px.
     * @param wysokosc
     * @param szerokosc 
     */
    public void ustawWymiaryKratki(int wysokosc, int szerokosc) {
        wysokoscKratki = wysokosc;
        szerokoscKratki = szerokosc;
    }
    
    /**
     * Funkcja odpowiadająca za rysowanie kontrolki
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.white);
        g.fillRect(0, 0, szerokoscKontrolki, wysokoscSiatki*wysokoscKratki+wysokoscNaglowka);
                
        // Rysowanie tytułu
        g.setColor(Color.black);
        g.drawRect(0, 0, 100+wyliczNadmiarowaSzerokosc(g, modelDiagramu.nazwa(), 80), 25);
        g.drawString(modelDiagramu.nazwa(), 10, 20);
        
        // Rysowanie obiektów          
        for (Obiekt o : modelDiagramu.zwrocListeObiektow()) {                
            rysujObiekt(g, o, wspolrzednaKonca);            
        }  
        
        // Rysowanie komunikatów
        for (Komunikat k : modelDiagramu.zwrocListeKomunikatow()) {            
            rysujKomunikat(g, k);
        }
        
        // Rysowanie obszarów wydzielonych
        for (ObszarWydzielony ow : modelDiagramu.zwrocListeObszarow()) {           
            rysujObszarWydzielony(g, ow);            
        }
        
    }
    
    /**
     * Funkcja rysująca obiekt (uczestnika diagramu)
     * @param g
     * @param o
     * @param wspolrzednaKonca 
     */
    private void rysujObiekt(Graphics g, Obiekt o, int wspolrzednaKonca) {
        
        // Punktów początkowych i końcowych nie rysujemy
        if (o.przedstawiaPunktSpecjalny()) {
            return;
        }
        
        int y0 = (o.zwrocPrzesuniecieNaglowka() + 1) * wysokoscKratki; // Opóźnienie rozpoczęcia w pikselach
        
        int yStart = y0 + wysokoscNaglowka;
        int yKoniec;
        
        int i = o.zwrocIndeks();
        int xStart = i*szerokoscKratki+skumulowanyNadmiar(i);
        g.setColor(Color.black);
             
        if (o.zwrocPrzesuniecieKonca() == 0) {
            yKoniec = wspolrzednaKonca;
        }
        else {
            yKoniec = wysokoscNaglowka + (o.zwrocPrzesuniecieKonca() + 1) * wysokoscKratki - wysokoscKratki/2;            
            //Narysowanie iksa
            g.drawLine(xStart + szerokoscKratki/2 + 10, yKoniec + 10, xStart + szerokoscKratki/2 - 10, yKoniec - 10);
            g.drawLine(xStart + szerokoscKratki/2 + 10, yKoniec - 10, xStart + szerokoscKratki/2 - 10, yKoniec + 10);
        }
            
        // Nagłówek obiektu
        if (o.przedstawiaUzytkownika()) {            
            //Rysowanie "ludzika"
            g.drawOval(xStart+ szerokoscKratki/2 - 10, y0 + 35, 20, 20);
            g.drawLine(xStart+ szerokoscKratki/2, y0 + 55, xStart+ szerokoscKratki/2, 75);
            g.drawLine(xStart+ szerokoscKratki/2 -15 , y0 + 60, xStart+ szerokoscKratki/2 + 15, 60);
            g.drawLine(xStart+ szerokoscKratki/2, y0 + 75, xStart+ szerokoscKratki/2 - 10, 90);
            g.drawLine(xStart+ szerokoscKratki/2, y0 + 75, xStart+ szerokoscKratki/2 + 10, 90);
            g.drawString(o.pelnaNazwa(), xStart+15, y0 + wysokoscNaglowka-15);
        }
        else {
            //Rysowanie zwykłego prostokąta
            g.drawRect(xStart+10, y0 + wysokoscNaglowka-50, szerokoscKratki-20+nadmiaroweSzerokosciObiektow[i], 35);
            g.drawString(o.pelnaNazwa(), xStart+15, y0 + wysokoscNaglowka-30);
        }        
            
        // Przerywana linia
        rysujPionowaPrzerywanaLinie(g, xStart+szerokoscKratki/2, yStart, yKoniec);
            
        // Czas życia
        List<Integer> czas = o.zwrocCzasZycia();
        for (int j = 0; j < czas.size(); j += 2) {
            g.setColor(Color.green);
            int y1 = czas.get(j)*wysokoscKratki + wysokoscNaglowka + wysokoscKratki/3;
            int y2 = czas.get(j+1)*wysokoscKratki + wysokoscNaglowka + wysokoscKratki*2/3;
            g.setColor(Color.black);
            g.drawRect(xStart+szerokoscKratki/2-5, y1, 10, y2-y1);
            g.setColor(Color.green);
            g.fillRect(xStart+szerokoscKratki/2-5+1, y1+1, 10-1, y2-y1-1);
        }
    }

    /**
     * Funkcja rysująca komunikat
     * @param g
     * @param k 
     */
    private void rysujKomunikat(Graphics g, Komunikat k) {
        
        // Pobieramy dane w postaci współrzędnich siatki (nie w pikselach)
        int xStart = k.indeksObiektuPoczatkowego();
        int xKoniec = k.indeksObiektuKoncowego();
        int yStart = k.indeksOstatniegoWiersza();
        
        if (xKoniec == -1) {
            xKoniec = modelDiagramu.zwrocLiczbeObiektow();
        }
        
        
        int x1, x2, y;
            
        if (xStart != xKoniec) { // Komunikat łączy dwa obiekty
            
            // Wyliczanie współrzędnych
            x1 = xStart * szerokoscKratki + szerokoscKratki / 2 + skumulowanyNadmiar(xStart);
            x2 = xKoniec * szerokoscKratki + szerokoscKratki / 2 + skumulowanyNadmiar(xKoniec);
            y = yStart * wysokoscKratki + wysokoscNaglowka + wysokoscKratki / 2;
            
            if (k.jestTworzeniem()) {
                if (x2 > x1) {
                    x2 = xKoniec * szerokoscKratki + 10 + skumulowanyNadmiar(xKoniec);
                }
                else {
                    x2 = (xKoniec+1) * szerokoscKratki - 10 + skumulowanyNadmiar(xKoniec+1);
                }                
            }
            
            // Rysowanie linii
            g.setColor(Color.black);
            if (k.jestPowrotny() || k.jestTworzeniem()) {
                rysujPoziomaPrzerywanaLinie(g, x1, y, x2);
            }
            else {
                g.drawLine(x1, y, x2, y);
            }
                
            // Rysowanie grota i podpisu            
            if (x1 < x2) {
                rysujGrotStrzalki(g, x2, y, k.jestWywolaniem() || k.jestUsuwaniem(), true);
                g.drawString(k.nazwa(), x1+25, y-5); 
            }
            else {
                rysujGrotStrzalki(g, x2, y, k.jestWywolaniem() || k.jestUsuwaniem(), false);
                g.drawString(k.nazwa(), x2+25, y-5);                
            } 
            
            // Obsługa komunikatów pochodzących z punktów początkowych/końcowych
            if (k.obiektPoczatkowyJestPunktemSpecjalnym()) {
                g.fillOval(x1-10, y-10, 20, 20);
            }
            if (k.obiektKoncowyJestPunktemSpecjalnym()) {
                g.fillOval(x2-10, y-10, 20, 20);
            }
                   
        }
        else { // Komunikat prowadzi do tego samego obiektu, z którego wychodzi
            
            // Wyliczanie współrzędnych
            int y1 = (yStart-1) * wysokoscKratki + wysokoscNaglowka + wysokoscKratki / 2;
            int y2 = yStart * wysokoscKratki + wysokoscNaglowka + wysokoscKratki / 2;                
            x2 = (xStart+1) * szerokoscKratki + skumulowanyNadmiar(xStart);
            x1 = x2 - szerokoscKratki / 2;
            
            // Rysowanie linii
            g.setColor(Color.black);
            if (k.jestPowrotny()) {
                rysujPoziomaPrzerywanaLinie(g, x1, y1, x2);
                rysujPionowaPrzerywanaLinie(g, x1, y1, y2);
                rysujPoziomaPrzerywanaLinie(g, x1, y2, x2);
            }
            else {
                g.drawLine(x1, y1, x2, y1);
                g.drawLine(x2, y1, x2, y2);
                g.drawLine(x2, y2, x1, y2);
            }
            
            
            // Rysowanie grota i podpisu
            rysujGrotStrzalki(g, x1, y2, k.jestWywolaniem(), false);
            g.drawString(k.nazwa(), x1+15, y1-5);
        }
    }

    /**
     * Funkcja rysująca obszar (blok) wydzielony.
     * @param g
     * @param ow 
     */
    private void rysujObszarWydzielony(Graphics g, ObszarWydzielony ow) {
        
        // Odczytanie współrzędnych w jednokstach siatki (nie w pikselach).
        int xStart = ow.indeksObiektuPoczatkowego();
        int xKoniec = ow.indeksObiektuKoncowego();
        int yKoniec = ow.indeksOstatniegoWiersza();
        int yStart = yKoniec - ow.zwrocWysokoscBloku() + 1;
        
            
        if (xStart > xKoniec) { // Zamiana miejscami
            int t = xKoniec;
            xKoniec = xStart;
            xStart = t;
        }
        
        boolean tylkoJednaKratka = false;
        if (xKoniec - xStart == 0) {
            tylkoJednaKratka = true;
        }
        
        // Wyliczenie watości w pikselach
        int x1 = xStart * szerokoscKratki + szerokoscKratki / 8  + skumulowanyNadmiar(xStart); // Początek bloku
        int xs = (xKoniec - xStart + 1) * szerokoscKratki - szerokoscKratki / 4 + skumulowanyNadmiar(xKoniec) - skumulowanyNadmiar(xStart); // Szerokosc bloku
        int y1 = yStart * wysokoscKratki + wysokoscNaglowka + wysokoscKratki / 8;        
        int ys = (yKoniec - yStart + 1) * wysokoscKratki - wysokoscKratki / 4;
                
        int szerokoscEtykiety = szerokoscKratki / 2 + this.wyliczNadmiarowaSzerokosc(g, ow.nazwa(), szerokoscKratki / 2 - 20);
        
        // Narysowanie głównego prostokąta
        g.setColor(Color.black);
        g.drawRect(x1, y1, xs, ys);
        
        // Narysowanie poletka na etykietę
        Polygon p = new Polygon();
        p.addPoint(x1+1, y1+1);
        p.addPoint(x1+szerokoscEtykiety, y1+1);
        p.addPoint(x1+szerokoscEtykiety, y1+5);
        p.addPoint(x1+szerokoscEtykiety-10, y1+15);
        p.addPoint(x1+1, y1+15);
        g.setColor(Color.white);
        g.fillPolygon(p);
        g.setColor(Color.black);
        g.drawLine(x1, y1+15, x1+szerokoscEtykiety-10, y1+15);
        g.drawLine(x1+szerokoscEtykiety-10, y1+15, x1+szerokoscEtykiety, y1+5);
        g.drawLine(x1+szerokoscEtykiety, y1+5, x1+szerokoscEtykiety, y1);
        
        // Rysowanie napisów
        g.drawString(ow.nazwa(), x1+5, y1+13);
        String komentarz = ow.zwrocKomentarz();
        if (!komentarz.isEmpty()) {
            if (tylkoJednaKratka) { // Narysuj pod etykietą
                g.drawString("[" + komentarz + "]", x1+10, y1+30);
            }
            else { // Narysuj obok etykiety
                g.drawString("[" + komentarz + "]", x1+szerokoscEtykiety+5, y1+13);
            }            
        }
    }
    
    /**
     * Funkcja pomocnicza rysująca pionową przerywaną linię.
     * @param g
     * @param xStart
     * @param yStart
     * @param yKoniec 
     */
    private void rysujPionowaPrzerywanaLinie(Graphics g, int xStart, int yStart, int yKoniec) {  
    
        if (yKoniec < yStart) {
            int t = yKoniec;
            yKoniec = yStart;
            yStart = t;
        }
        for (int i = yStart; i < yKoniec; i += 10) {
            g.drawLine(xStart, i+2, xStart, i+8);
        }
    }
    
    private void rysujPoziomaPrzerywanaLinie(Graphics g, int xStart, int yStart, int xKoniec) { 
        if (xKoniec < xStart) {
            int t = xKoniec;
            xKoniec = xStart;
            xStart = t;
        }
        for (int i = xStart; i < xKoniec; i += 10) {
            g.drawLine(i+2, yStart, i+8, yStart);
        }
    }
    
    /**
     * Funkcja rysująca grot strzałki.
     * @param g
     * @param x Współrzędna czubka grota
     * @param y Współrzędna czubka grota
     * @param wypelniona Informacja, czy grot ma być pełny
     * @param wPrawo Informacja, czy strzałka jest skierowana w prawo
     */
    private void rysujGrotStrzalki (Graphics g, int x, int y, boolean wypelniona, boolean wPrawo) {
        
        // Wyznakenie współrzędnej podstawy grota
        int x2;
        if (wPrawo) {
            x2 = x - 15;
        }
        else {
            x2 = x + 15;
        }
        
        // Rysowanie
        if (wypelniona) {
            Polygon p = new Polygon();
            p.addPoint(x, y);
            p.addPoint(x2, y+10);
            p.addPoint(x2, y-10);
            g.fillPolygon(p);
        }
        else {
            g.drawLine(x, y, x2, y+10);
            g.drawLine(x, y, x2, y-10);                    
        }
            
    }

    /**
     * Funkcja ładująca model diagramu do kontrolki i generująca kontrolkę od nowa
     * @param modelDiagramu 
     */
    public void ladujPonownie(Diagram modelDiagramu) {
        
        this.modelDiagramu = modelDiagramu;
        wysokoscNaglowka = 125;
        
        Integer wysokosc = modelDiagramu.zwrocWysokoscWiersza();
        if (wysokosc == null) {
            wysokoscKratki = 50;
        }
        else {
            wysokoscKratki = wysokosc;
        }
        
        Integer szerokosc = modelDiagramu.zwrocSzerokoscKolumny();
        if (szerokosc == null) {
            szerokoscKratki = 150;
        }
        else {
            szerokoscKratki = szerokosc;
        }       
        
        odswiez();
    }

    /**
     * Funkcja osświeżająća kontrolkę
     */
    public void odswiez() {
        
        // Wyliczenie nadmiarowych szerokości kolumn
        int liczbaObiektow = modelDiagramu.zwrocLiczbeObiektow();
        nadmiaroweSzerokosciObiektow = new int[liczbaObiektow];
        LinkedList<Obiekt> listaObiektow = modelDiagramu.zwrocListeObiektow();
        for (int i = 0; i < liczbaObiektow; ++i) {
            int nadmiar = wyliczNadmiarowaSzerokosc(getGraphics(), listaObiektow.get(i).pelnaNazwa(), szerokoscKratki-30);
            nadmiaroweSzerokosciObiektow[i] = nadmiar;
        }
        
        // Ustawienie wymiarów kontrolki
        szerokoscSiatki = liczbaObiektow;
        wysokoscSiatki = modelDiagramu.zwrocLiczbeWierszy();
        wspolrzednaKonca = wysokoscNaglowka + wysokoscSiatki*wysokoscKratki;
        szerokoscKontrolki = szerokoscSiatki*szerokoscKratki+skumulowanyNadmiar(szerokoscSiatki)+szerokoscKratki/2;
        
        // Obsłużenie przypadku pustego wykresu
        if (szerokoscSiatki == 0) {
            szerokoscSiatki = 1;
        }
        
        setSize(szerokoscKontrolki, wspolrzednaKonca);        
        setPreferredSize(new Dimension(szerokoscKontrolki, wspolrzednaKonca));
        
        revalidate();
        repaint();
    }

    /**
     * Funkcja eksportuje obraz przedstawiający diagram do pliku podanego jako parametr
     * @param wybranyPlik
     * @throws IOException 
     */
    public void ekspotrujPlik(File wybranyPlik) throws IOException {
        
        BufferedImage zbuforowanyPlik = new BufferedImage(szerokoscKontrolki, wspolrzednaKonca, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = zbuforowanyPlik.createGraphics();
        paint(g);
        ImageIO.write(zbuforowanyPlik, "png", wybranyPlik);
    }
    
    /**
     * Funkcja mierząca szerokość tekstu do wypisania
     * @param g Obiekt typu Graphics używany do rysowania.
     * @param tekst Tekst, którego szerokość ma być zmierzona.
     * @return Szerokość tekstu w pikselach
     */
    private int wyliczNadmiarowaSzerokosc(Graphics g, String tekst, int planowanaLiczbaPikseli) {
        
        // Wyliczenie szerokosci tekstu
        FontMetrics metrics = g.getFontMetrics();
        int szerokosc = metrics.stringWidth(tekst);
        
        // Odjęcie planowanej szerokośći od wyliczonej szerokości w celu obliczenia nadmiaru
        int nadmiar = 0;
        if (szerokosc > planowanaLiczbaPikseli) {
            nadmiar = szerokosc - planowanaLiczbaPikseli;
        }
        return nadmiar;
    }
    
    /**
     * Funkcja zwraca sumę nadmiarowych długości napisów w kolumnach o indeksie mniejszym od podanego parametru.
     * @param nrKolumny
     * @return 
     */
    private int skumulowanyNadmiar(int nrKolumny) {
        
        int stop = nrKolumny;
        if (stop > modelDiagramu.zwrocLiczbeObiektow()) {
            stop = modelDiagramu.zwrocLiczbeObiektow();
        } 
        
        int wynik = szerokoscKratki/2; // Żeby był margines
        
        for (int i=0; i<stop; ++i) {
            wynik += nadmiaroweSzerokosciObiektow[i];
        }
        
        return wynik;
    }
}


