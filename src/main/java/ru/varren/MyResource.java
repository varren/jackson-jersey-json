package ru.varren;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Path("readCsv")
public class MyResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map> readCsvservice(){

        String csvFile = System.getProperty("user.dir") + "/resources/branch_profitability.csv";
        String line = "";
        String cvsSplitBy = ",";

        List<Map> branchList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                HashMap<String, String> branch = new HashMap<>();
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                branch.put("Zone", country[0]);
                branch.put("State", country[1]);
                branch.put("City", country[2]);
                branch.put("Location", country[3]);
                branch.put("brach", country[4]);
                branch.put("Employee", country[5]);
                branch.put("EOP", country[6]);
                branchList.add(branch);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return branchList;
    }
}