package com.hakansander.currency.scrapper;

import com.hakansander.currency.constants.ScrapperConstants;
import com.hakansander.currency.model.CurrencyResponse.realTime.ScrappedCurrency;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class WebScrapper {

    private ScrappedCurrency scrappedCurrency;

    private static final String URL = "https://www.investing.com/currencies/single-currency-crosses";

    private static final String SCRAPPED_CLASS_NAME_USD_TRY = "pid-18-bid";
    private static final String SCRAPPED_CLASS_NAME_EUR_TRY = "pid-66-bid";
    private static final String SCRAPPED_CLASS_NAME_GBP_TRY = "pid-97-bid";

    @PostConstruct
    private void runScrap() {
        log.info("[runScrap] Method is called on application start");
        //periodicallyScrap();
    }

    public List<ScrappedCurrency> scrap() {

        log.info("[scrap] Method is called for scrapping the topics");

        List<ScrappedCurrency> finalScrappedCurrency = new ArrayList<>();

        Document doc = null;

        Response response = null;
        try {
            response = Jsoup.connect(URL)
                    .userAgent(ScrapperConstants.USER_AGENT_MOZILLA)
                    .execute();
        } catch (UnknownHostException e) {
            log.error("[scrap] Could not connect to the given url");
        } catch (IOException e) {
            log.error("[scrap] IOException has been occurred while trying to connect to the given url :: ", e);
        } catch (Exception e) {
            log.error("[scrap] An exception occurred :: ", e);
        }

        try {
            doc = Jsoup.connect(URL)
                    .userAgent(ScrapperConstants.USER_AGENT_MOZILLA)
                    .header("Accept", ScrapperConstants.ACCEPT)
                    .header("Accept-Encoding", ScrapperConstants.ACCEPT_ENCODING)
                    .header("Accept-Language", ScrapperConstants.ACCEPT_LANGUAGE_EN)
                    .header("Cache-Control", ScrapperConstants.CACHE_CONTROL)
                    .header("Connection", ScrapperConstants.CONNECTION)
                    .header("Host", ScrapperConstants.HOST)
                    .header("Referer", ScrapperConstants.REFERER)
                    .cookies(response.cookies())
                    .timeout(5000)
                    .get();
        } catch (UnknownHostException e) {
            log.error("[scrap] Could not connect to the given url");
        } catch (IOException e) {
            log.error("[scrap] IOException has been occurred while trying to connect to the given url :: ", e);
        } catch (Exception e) {
            log.error("[scrap] An exception occurred :: ", e);
        }

        String targetValue = doc.getElementsByClass(SCRAPPED_CLASS_NAME_USD_TRY).get(0).childNode(0).toString();
        scrappedCurrency = new ScrappedCurrency("TRY", "USD", targetValue);
        finalScrappedCurrency.add(scrappedCurrency);

        targetValue = doc.getElementsByClass(SCRAPPED_CLASS_NAME_EUR_TRY).get(0).childNode(0).toString();
        scrappedCurrency = new ScrappedCurrency("TRY", "EUR", targetValue);
        finalScrappedCurrency.add(scrappedCurrency);

        targetValue = doc.getElementsByClass(SCRAPPED_CLASS_NAME_GBP_TRY).get(0).childNode(0).toString();
        scrappedCurrency = new ScrappedCurrency("TRY", "GBP", targetValue);
        finalScrappedCurrency.add(scrappedCurrency);

        log.info("[scrap] Scrapped the currency data :: currency={}", finalScrappedCurrency);

        return finalScrappedCurrency;
    }
}
