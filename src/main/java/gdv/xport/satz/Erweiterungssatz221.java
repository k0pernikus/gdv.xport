/*
 * Copyright (c) 2010 - 2012 by Oli B.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 18.11.2010 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;
import gdv.xport.feld.*;

import org.apache.commons.logging.*;

/**
 * Klasse fuer die Satzart 0221 (Erweiterungssatz).
 * 
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (18.11.2010)
 */
public class Erweiterungssatz221 extends Spartensatz {
    
    /** 221 Erweiterungssatz. */
    public static final int SATZART = 221;

    private static final Log log = LogFactory.getLog(Erweiterungssatz221.class);

    /**
     * Dieser Konstruktor wird fuer die SatzFactory benoetigt.
     */
    public Erweiterungssatz221() {
        super(SATZART);
    }

    /**
     * Konstruktor fuer die gewuenschte Sparte.
     * 
     * @param sparte z.B. 70 (Rechtsschutz)
     */
    public Erweiterungssatz221(final int sparte) {
        this(sparte, getNumberOfTeildatensaetzeFor(sparte));
    }

    /**
     * Konstruktor fuer die gewuenschte Sparte.
     * 
     * @param sparte z.B. 70 (Rechtsschutz)
     * @param n Anzahl Teildatensaetze
     */
    public Erweiterungssatz221(final int sparte, final int n) {
        super(SATZART, sparte, n);
        this.setUpDatenfelder(sparte);
    }
    
    private static int getNumberOfTeildatensaetzeFor(final int sparte) {
        switch (sparte) {
            case 0:
            case 40:
            case 51:
            case 52:
            case 53:
            case 55:
            case 59:
            case 80:
            case 110:
            case 130:
            case 140:
            case 170:
            case 190:
            case 510:
                return 1;
            case 30:
            case 54:
            case 70:
                return 2;
            default:
                log.warn("unknown Sparte " + sparte + " -> mapped to 1 Teildatensatz");
                return 1;
        }
    }
    
    /**
     * Legt die entsprechende Anzahl von Teildatensaetze fuer die angegebene
     * Sparte an.
     *
     * @param x Sparte (z.B. 30)
     */
    protected void createTeildatensaetzeFor(final int x) {
        this.createTeildatensaetze(getNumberOfTeildatensaetzeFor(x));
    }

    /**
     * Initialisiert die Teildatensaetze fuer die angegebene Sparte.
     *
     * @param sparte Sparte (z.B. 30)
     */
    protected void setUpDatenfelder(final int sparte) {
        switch (sparte) {
            case 30:
                this.setUpDatenfelder30();
                break;
            case 51:
                this.setUpDatenfelder51();
                break;
            case 52:
                this.setUpDatenfelder52();
                break;
            case 53:
                this.setUpDatenfelder53();
                break;
            case 70:
                this.setUpDatenfelder70();
                break;
            default:
                log.warn("Sparte " + sparte + " not yet fully supported");
                this.setUpTeildatensaetze();
                this.addFiller();
                break;
        }
    }
    
    private void setUpDatenfelder30() {
        this.setUpTeildatensatz30(2, this.getTeildatensatz(1));
        this.setUpTeildatensatz30(3, this.getTeildatensatz(2));
    }

    private void setUpTeildatensatz30(final int n, final Teildatensatz tds) {
        this.setUpTeildatensatz(tds);
        switch(n) {
            case 2:     // Teildatensatz 2
                tds.add(new NumFeld(LFD_NUMMER_VP_PERSONENGRUPPE, 6, 43));
                tds.add(new NumFeld(SATZNUMMER, 1, 49, n));
                tds.add(new NumFeld(TOD, 14, 50).mitNachkommastellen(2));
                tds.add(new NumFeld(INVALIDITAET, 14, 64).mitNachkommastellen(2));
                tds.add(new NumFeld(TAGEGELD1, 10, 78).mitNachkommastellen(2));
                tds.add(new NumFeld(TAGEGELD2, 10, 88).mitNachkommastellen(2));
                tds.add(new NumFeld(KRANKENHAUSTAGEGELD, 10, 98).mitNachkommastellen(2));
                tds.add(new NumFeld(GENESUNGSGELD, 10, 108).mitNachkommastellen(2));
                tds.add(new NumFeld(UEBERGANGSENTSCHAEDIGUNG, 14, 118).mitNachkommastellen(2));
                tds.add(new NumFeld(HEILKOSTEN, 14, 132).mitNachkommastellen(2));
                tds.add(new NumFeld(FESTE_RENTE, 14, 146).mitNachkommastellen(2));
                tds.add(new NumFeld(KOSMETISCHE_OPERATIONEN, 14, 160).mitNachkommastellen(2));
                tds.add(new NumFeld(KURKOSTEN, 14, 174).mitNachkommastellen(2));
                tds.add(new NumFeld(BERGUNGSKOSTEN, 14, 188).mitNachkommastellen(2));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 54, 202));
                tds.add(new Zeichen(ZUSAETZLICHE_SATZKENNUNG, 256, 'X'));
                break;
            case 3:     // Teildatensatz 3
                tds.add(new NumFeld(SATZNUMMER, 1, 43, n));
                tds.add(new NumFeld(SERVICELEISTUNGEN, 14, 44).mitNachkommastellen(2));
                tds.add(new AlphaNumFeld(REFERENZNUMMER, 7, 58));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 185, 65));
                tds.add(new NumFeld(SATZNUMMERWIEDERHOLUNG, 1, 250));
                tds.add(new NumFeld(LFD_NUMMER_VP_PERSONENGRUPPE, 6, 251));
                break;
            default:
                throw new IllegalArgumentException("unbekannte Teildatensatz-Nr.: " + n);
        }
    }
    
    /**
     * Sparte 51 (KFZ - Fahrzeughaftpflicht) wurde freundlicherweise von
     * Igor Narodetskyi zur Verfuegung gestellt.
     * 
     * @since 0.5.1
     */
    private void setUpDatenfelder51() {
        // Teildatensatz 1
        add(new NumFeld(KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1, 14, 43).mitNachkommastellen(2));
        add(new NumFeld(KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2, 14, 57).mitNachkommastellen(2));
        add(new NumFeld(KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3, 14, 71).mitNachkommastellen(2));
        add(new Betrag(KH_BEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 85));
        add(new Betrag(KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 12, 97));
        add(new Betrag(KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 12, 109));
        add(new Betrag(TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN, 12, 121));
        add(new Betrag(FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH, 12, 133));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 145));
        add(new AlphaNumFeld(LFD_NUMMER, 4, 152));
        add(new AlphaNumFeld(PERSONENNUMMER_LFD_NUMMER, 17, 156));
    }
    
    /**
     * Sparte 52 (KFZ - Fahrzeugvoll) wurde freundlicherweise von
     * Igor Narodetskyi zur Verfuegung gestellt.
     * 
     * @since 0.5.1
     */
    private void setUpDatenfelder52() {
        // Teildatensatz 1
        add(new Betrag(KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 43));
        add(new Betrag(KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 12, 55));
        add(new Betrag(KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 12, 67));
        add(new Betrag(FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO, 12, 79));
        add(new Betrag(TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN, 12, 91));
        add(new Betrag(FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO_IM_RAHMEN_DER_VOLLKASKO,
                12, 103));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 115));
        add(new AlphaNumFeld(LFD_NUMMER, 4, 122));
        add(new AlphaNumFeld(PERSONENNUMMER_LFD_NUMMER, 17, 126));
    }

    /**
     * Sparte 53 (KFZ - Fahrzeugteil) wurde freundlicherweise von
     * Igor Narodetskyi zur Verfuegung gestellt.
     * 
     * @since 0.5.1
     */
    private void setUpDatenfelder53() {
        // Teildatensatz 1
        add(new Betrag(KFT_BEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 43));
        add(new Betrag(KFT_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 12, 55));
        add(new Betrag(KFT_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 12, 67));
        add(new Betrag(FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO, 12, 79));
        add(new Betrag(TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGTEIL_IN_WAEHRUNGSEINHEITEN, 12, 91));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 103));
        add(new AlphaNumFeld(LFD_NUMMER, 4, 110));
        add(new AlphaNumFeld(PERSONENNUMMER_LFD_NUMMER, 17, 114));
    }

    private void setUpDatenfelder70() {
        this.setUpTeildatensatz70(1, this.getTeildatensatz(1));
        this.setUpTeildatensatz70(2, this.getTeildatensatz(2));
    }

    private void setUpTeildatensatz70(final int n, final Teildatensatz tds) {
        this.setUpTeildatensatz(tds);
        tds.add(new AlphaNumFeld(RISIKOGRUPPE_RISIKOART, 5, 43));
        tds.add(new NumFeld(LFD_NUMMER, 5, 48));
        tds.add(new NumFeld(SATZNUMMER, 1, 53, n));
        switch(n) {
            case 1:     // Teildatensatz 1
                tds.add(new NumFeld(DECKUNGSSUMME_IN_WAEHRUNGSEINHEITEN, 14, 54).mitNachkommastellen(2));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 189, 68));
                break;
            case 2:     // Teildatensatz 2
                tds.add(new NumFeld(ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN, 12, 54).mitNachkommastellen(2));
                tds.add(new NumFeld(ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN, 12, 66).mitNachkommastellen(2));
                tds.add(new NumFeld(ABSCHLAG1_IN_WAEHRUNGSEINHEITEN, 12, 78).mitNachkommastellen(2));
                tds.add(new NumFeld(ABSCHLAG2_IN_WAEHRUNGSEINHEITEN, 12, 90).mitNachkommastellen(2));
                tds.add(new NumFeld(ABSCHLAG3_IN_WAEHRUNGSEINHEITEN, 12, 102).mitNachkommastellen(2));
                tds.add(new AlphaNumFeld(REFERENZNUMMER, 7, 114));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 136, 121));
                break;
            default:
                throw new IllegalArgumentException("unbekannte Teildatensatz-Nr.: " + n);
        }
    }

}

