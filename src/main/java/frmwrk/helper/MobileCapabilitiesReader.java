package frmwrk.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MobileCapabilitiesReader {
	
	private static JSONArray parseJSON(String jsonLocation) {
        InputStream is = null;
		try {
			is = new FileInputStream(jsonLocation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (is == null) {
            throw new NullPointerException("Cannot find file " + jsonLocation);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONArray array = new JSONArray(tokener);    
        return array;
    }

    private static JSONObject getCapability(String deviceName, String jsonLocation) throws Exception {
        JSONArray capabilitiesArray = parseJSON(jsonLocation);
        for (Object jsonObj : capabilitiesArray) {
            JSONObject capability = (JSONObject) jsonObj;
            if (capability.get("deviceName").toString().equalsIgnoreCase(deviceName)) {
                return (JSONObject) capability.get("caps");
            }
        }
        return null;
    }

	@SuppressWarnings("unchecked")
	private static HashMap<String, Object> convertCapsToHashMap(String deviceName, String jsonLocation) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(getCapability(deviceName, jsonLocation).toString(), HashMap.class);
    }

    public static DesiredCapabilities getDesiredCapabilities(String deviceName, String jsonLocation) throws Exception {
    	jsonLocation = jsonLocation.replace("/", File.separator);
    	jsonLocation = jsonLocation.replace("\\", File.separator);
        HashMap<String, Object>  caps = convertCapsToHashMap(deviceName, jsonLocation);
        return new DesiredCapabilities(caps);
    }
}


