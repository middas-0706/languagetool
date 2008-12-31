/* LanguageTool, a natural language style checker 
 * Copyright (C) 2006 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package de.danielnaber.languagetool.dev;

import java.util.Properties;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import de.danielnaber.languagetool.Language;
import de.danielnaber.languagetool.tools.StringTools;

/** Used for creating ooolocales.properties file that
 *  defines a property that is needed to build
 *  Linguistic.xcu. Run internally by the ant build.
 *  
 * @author Marcin Miłkowski
 *
 */
public final class PrintLocales {


  public static void main(final String[] args) throws IOException{
    final PrintLocales prg = new PrintLocales();
    prg.run();
  }

  private void run() throws IOException { 
    String locales = "";
    for (final Language element : Language.LANGUAGES) {
      if (!element.equals(Language.DEMO)) {
        for (final String variant : element.getCountryVariants()) {
          if (!StringTools.isEmpty(locales)) {
            locales = locales + " "  + element.getShortName() + "-" + variant;
          } else {
            locales = element.getShortName() + "-" + variant;
          }
        }
      }
    }
    //change attribute to writable as the property file is in the repo
    final File f = new File("ooolocales.properties");
    f.setWritable(true);
    final Properties propLoc = new Properties();
    propLoc.setProperty("countryvariants", locales);
    propLoc.store(new FileOutputStream("ooolocales.properties"), "Locales");    
  }
}
