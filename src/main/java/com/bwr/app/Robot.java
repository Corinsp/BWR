package com.bwr.app;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

@Data
public class Robot extends TimerTask {

    private Integer id;
    private State state;
    private Timestamp lastUpdateTime;

    public Robot(Integer id, State state, Timestamp lastUpdateTime) {
        this.id = id;
        this.state = state;
        this.lastUpdateTime = lastUpdateTime;
        Timer timer = new Timer();
        timer.schedule(this, 0, 2000);
    }

    @Override
    public void run() {
        System.out.println("Alive");
        URL url = null;
        try {
            url = new URL("http://localhost:8080/robot?robotId="+this.id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
