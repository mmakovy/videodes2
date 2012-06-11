/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Close;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.Open;

/**
 *
 * @author AjkoST
 */
public class BaseXDriver {

    Context context = new Context();

    public static void openCollection(Context context) throws BaseXException {
        if (context == null) {
            throw new NullPointerException("context");
        }
        new Open("VideoDes2DB").execute(context);
    }

    public static void createCollection(Context context) throws BaseXException {
        if (context == null) {
            throw new NullPointerException("context");
        }

        new CreateDB("VideoDes2DB", "VideoDes2DB/").execute(context);
    }

    public static void closeCollection(Context context) throws BaseXException {
        if (context == null) {
            throw new NullPointerException("context");
        }
        new Close().execute(context);
    }
}
