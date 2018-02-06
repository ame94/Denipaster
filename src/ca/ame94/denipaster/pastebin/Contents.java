package ca.ame94.denipaster.pastebin;

import ca.ame94.denipaster.util.Config;
import ca.ame94.denipaster.util.DateTime;
import ca.ame94.denipaster.util.Logger;
import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.response.Response;

import java.util.ArrayList;
import java.util.List;

public class Contents {

    public static ArrayList<String> getPastebinDirectory() {
        ArrayList<String> directory = new ArrayList<>();

        final PastebinFactory factory = new PastebinFactory();
        final Pastebin pastebin = factory.createPastebin(Config.getAPIKey());

        Response<List<Paste>> resp = pastebin.getPastesOf(Config.getUserKey());

        if (resp.hasError()) {
            Logger.Warning("Could not fetch Pastebin user information!");
            directory = null;
        } else {
            for (Paste p : resp.get()) {
                directory.add(p.getKey() + ": \"" + p.getTitle() + "\" " + DateTime.getDateFromUnixtime(p.getPublishDate()) + " :: " + p.getSize() + " bytes");
            }
        }

        return directory;
    }

    public static String getPaste(String pasteID) {
        String contents = null;

        PastebinFactory factory = new PastebinFactory();
        Pastebin pastebin = factory.createPastebin(Config.getAPIKey());
        Response<String> pasteResponse = pastebin.getRawPaste(pasteID);
        if (pasteResponse.hasError()) {
            Logger.Warning("Unable to read paste!");
        } else {
            Logger.Info("Fetched " + pasteID);
            contents = pasteResponse.get();
        }

        return contents;
    }

}
