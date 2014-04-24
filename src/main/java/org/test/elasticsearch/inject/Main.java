package org.test.elasticsearch.inject;

public class Main {

    public static void main(String[] args) {

        String data = new Data().getData();
        String index = "adaptimo";
        String documentName = "affaire";

        int limit;
        if(args == null || args.length < 1) {
            limit = 10000;
        } else {
            limit = Integer.valueOf(args[0]);
        }

        Inject inject = new Inject();
        inject.inject(index, documentName, data, limit);
    }

}
