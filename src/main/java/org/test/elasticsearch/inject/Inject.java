package org.test.elasticsearch.inject;

import net._01001111.text.LoremIpsum;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Inject data in elasticsearch.
 */
public class Inject {

    public void inject(String index, String documentName, String data, int limit) {
        ElasticSearchProvider elasticSearchProvider = new ElasticSearchProvider();
        elasticSearchProvider.init();

        Client client = elasticSearchProvider.getClient();

        LoremIpsum jlorem = new LoremIpsum();


        for(int i=0; i<limit; i++) {
            String id = getRandom();

            // add field test
            // String data2 = data.replace("France","USA");
            StringBuffer strBuffer = new StringBuffer("{");
            boolean isFirst=true;
            for(int j=0; j<600; j++) {
                if(isFirst) {
                    isFirst = false;
                } else {
                    strBuffer.append(", ");
                }
                strBuffer.append("\"field_"+j+"\":\""+jlorem.words(3)+"\"");
            }

            strBuffer.append("}");
            String data2 = strBuffer.toString();

            injectData(index, documentName, data2, id, client);
            System.out.println("inject : "+id);
        }

        elasticSearchProvider.close();
    }

    public void injectData(String index, String documentName, String data, String id, Client client) {
        IndexResponse response = client.prepareIndex(index, documentName, id)
                .setSource(data)
                .execute().actionGet();
    }

    private static final DecimalFormat timeFormat4 = new DecimalFormat("0000;0000");

    public static byte[] getSidWithCalendar() {
        Calendar cal = Calendar.getInstance();
        String val = String.valueOf(cal.get(Calendar.YEAR));
        val = val + timeFormat4.format(cal.get(Calendar.DAY_OF_YEAR));
        val += UUID.randomUUID().toString().replaceAll("-", "");
        return val.getBytes();
    }

    public String getRandom() {
        String str = new String(getSidWithCalendar());
        return str;
    }

}
