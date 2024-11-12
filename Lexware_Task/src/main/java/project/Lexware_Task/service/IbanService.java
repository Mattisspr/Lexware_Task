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
        log.info(ibanInformation.toString());
        this.validateIban(ibanInformation);
        JSONObject bankData = ibanInformation.getJSONObject("bankData");
        String bankName = bankData.getString("name");
        log.info(bankName);

        Iban ibanInfo = new Iban();
        ibanInfo.setBankName(bankData.getString("name"));
        ibanInfo.setIban(ibanInformation.getString("iban"));
        log.info(ibanInfo.toString());
        ibanRepository.save(ibanInfo);
        List<Iban> ibanList= ibanRepository.findAll();
        ibanList.forEach((iban1 -> log.info(iban1.getBankName() + " + " + iban1.getIban())));
        return bankName;
    }

    private void validateIban(JSONObject ibanInformation) {
        if (ibanInformation.isEmpty()) {
            throw new IllegalArgumentException("Fehler bei der Bankabfrage");
        }
        if (!ibanInformation.getBoolean("valid")) {
            JSONArray message = ibanInformation.getJSONArray("messages");
            throw new IllegalArgumentException(message.toString());
        }
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
            JSONObject bankDetails = new JSONObject(content.toString());
            return bankDetails;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}
