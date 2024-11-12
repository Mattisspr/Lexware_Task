package project.Lexware_Task.service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Lexware_Task.model.Iban;
import project.Lexware_Task.repository.IbanRepository;

@Service
public class IbanService {

    @Autowired
    private IbanRepository ibanRepository;

    private static final Logger log = LoggerFactory.getLogger(IbanService.class);

    public String getBankByIban(String iban) {
        JSONObject ibanInformation = this.getIbanInformation(iban);

        Iban ibanInfo = new Iban();
        ibanInfo.setBankName(ibanInformation.getJSONObject("bankData").getString("name"));
        ibanInfo.setIban(ibanInformation.getString("iban"));
        log.info(ibanInfo.getBankName());

        ibanRepository.save(ibanInfo);
        List<Iban> ibanList= ibanRepository.findAll();
        ibanList.forEach((iban1 -> log.info("Iban: " + iban1.getIban() + " gehört zur Bank: " + iban1.getBankName())));

        return ibanInfo.getBankName();
    }

    private JSONObject getIbanInformation(String iban) {
        String apiUrl = "https://openiban.com/validate/" + iban + "?getBIC=true&validateBankCode=true";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            log.info(content.toString());

            return new JSONObject(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}
