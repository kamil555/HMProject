package team6.tacoma.uw.edu.hmproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Class for making Users of App
 */
public class Users {

        private String mUsername;
        private String mPassword;
        public static final String USERNAME = "username", PASSWORD = "password";

    /**
     * Constructor that assigns variables
     * @param username
     * @param password
     */
    Users(String username, String password){
            this.mUsername = username;
            this.mPassword = password;
        }

    /**
     *  Returns Username
     * @return - string
     */
        String getUsername () {
            return mUsername;
        }

    /**
     *  Returns password
     * @return - string
     */
        String getPassword(){
            return mPassword;
        }

        /**
         * Parses the json string, returns an error message if unsuccessful.
         * Returns course list if success.
         * @param usersJSON
         * @return reason or null if successful.
         */
        public static String parseUsersJSON(String usersJSON, List<Users> usersList) {
            String reason = null;
            if (usersJSON != null) {
                try {
                    JSONArray arr = new JSONArray(usersJSON);

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        Users course = new Users(obj.getString(Users.USERNAME), obj.getString(Users.PASSWORD));
                        usersList.add(course);
                    }
                } catch (JSONException e) {
                    reason =  "Unable to parse data, Reason: " + e.getMessage();
                }

            }
            return reason;
        }

}
