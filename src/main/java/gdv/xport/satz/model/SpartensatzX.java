/*
 * Copyright (c) 2011, 2012 by Oli B.
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
 * (c)reated 06.04.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import gdv.xport.satz.feld.FeldX;

import java.util.Map;

import org.apache.commons.logging.*;

/**
 * Dies ist die gemeinsame Oberklasse aller Saetze in diesem Package, die
 * nach dem SOP-Muster aufgebaut sind und eine Sparte besitzen.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (06.04.2011)
 */
public abstract class SpartensatzX extends SatzX {

    private static final Log log = LogFactory.getLog(SpartensatzX.class);

    /** Wird fuer den Default-Ctor gebraucht. */
    protected static final int UNKNOWN_SPARTE = 0;

    /**
     * Instantiates a new spartensatz x.
     *
     * @param satzart the satzart
     * @param felder the felder
     */
    public SpartensatzX(final int satzart, final Enum<?>[] felder) {
        super(satzart, felder);
    }

    /**
     * Instantiates a new spartensatz x.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     */
    public SpartensatzX(final int satzart, final int sparte) {
        super(satzart, FeldX.values());
        this.setSparte(sparte);
    }

    /**
     * Instantiates a new spartensatz x.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @param felder the felder
     */
    public SpartensatzX(final int satzart, final int sparte, final Enum<?>[] felder) {
        super(satzart, sparte, felder);
    }

    /**
     * Liefert die Mapping-Tabelle der abgeleiteten Klasse.
     *
     * @return the mapping
     */
    protected abstract Map<Integer, Enum<?>[]> getMapping();

    /**
     * Liefert die entsprechende Enum-Felder zur angeforderten Spalte zurueck.
     *
     * @param sparte Sparte
     * @return the Enum-Felder
     */
    protected Enum<?>[] getFelderFor(final int sparte) {
        Map<Integer, Enum<?>[]> map = this.getMapping();
        Enum<?>[] felder = map.get(sparte);
        if (felder == null) {
            if (sparte == UNKNOWN_SPARTE) {
                return FeldX.values();
            }
            return FeldX.values();
        }
        return felder;
    }

    /**
     * Abhaengig von der Sparte muessen wir hier noch die verschiedenen
     * Teildatensaetze aufsetzen.
     *
     * @param x Sparte (z.B. 30)
     * @see gdv.xport.satz.Datensatz#setSparte(int)
     */
    @Override
    public void setSparte(final int x) {
        if (this.getSparte() == x) {
            log.debug("nothing to do here - old Sparte = new Sparte (" + x + ")");
            return;
        }
        Enum<?>[] felder = this.getFelderFor(x);
        this.setUpTeildatensaetze(felder);
        super.setSparte(x);
    }

}

