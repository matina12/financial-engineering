package financial.engineering.ch1;

import java.util.Random;

public class BlackScholesPricingApp {

    Random in = new Random();

    public static void main(String[] args) {

        //Non dividends Black-Scholes
        double firstCallPrice=callPrice(28, 40, 0.06, Math.sqrt(0.5), 0.5);
        System.out.println("The price of this call option is: " + firstCallPrice);

        double firstPutPrice = putPrice(20, 20, 0.08, Math.sqrt(0.36), 0.5);
        System.out.println("The price of this call option is: " + firstPutPrice);

        //Dividend Black-Scholes

        double firstCallDiv = callPriceDiv(930, 900, 0.03, 0.08, 0.20, (double) 1 / 6);
        System.out.println("The price of this call option is : " + firstCallDiv);

    }

    public static double cumulativeNormal(double x)
    {
        int neg = (x < 0d) ? 1 : 0;
        if ( neg == 1)
            x *= -1;

        double k = (1 / ( 1 + 0.2316419 * x));
        double y = (((( 1.330274429 * k - 1.821255978) * k + 1.781477937) *
                k - 0.356563782) * k + 0.319381530) * k;
        y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

        return (1 - neg) * y + neg * (1 - y);
    }



    // Non dividends Black-Scholes Model
    public static double callPrice(double s, double x, double r, double sigma, double t){
        double callPriceBlack = 0;
        double d1 = (Math.log(s/x) + (r + (sigma * sigma)/2)*t)/ sigma * Math.sqrt(t);
        double d2 = d1 - sigma* Math.sqrt(t);
        callPriceBlack = s * cumulativeNormal(d1) - x*Math.exp(-r*t)* cumulativeNormal(d2);
        return callPriceBlack;
    }

    public static double putPrice(double s, double x, double r, double sigma, double t){
        double putPriceBlack = 0;
        double d1 = (Math.log(s/x) + (r + (sigma * sigma)/2)*t)/ sigma * Math.sqrt(t);
        double d2 = d1 - sigma* Math.sqrt(t);
        putPriceBlack = x*Math.exp(-r*t)* cumulativeNormal(-d2) - s * cumulativeNormal(-d1) ;
        return putPriceBlack;
    }

    //Dividends Black-Scholes Model
    public static double callPriceDiv(double s, double x, double q ,double r, double sigma, double t){
        double callPriceBlackDiv = 0;
        double d1 = (Math.log(s/x) + (r - q + (sigma * sigma)/2)*t)/ sigma * Math.sqrt(t);
        double d2 = d1 - sigma* Math.sqrt(t);
        callPriceBlackDiv = s * Math.exp(-q*t) *cumulativeNormal(d1) - x*Math.exp(-r*t)* cumulativeNormal(d2);
        return callPriceBlackDiv;
    }

    public static double putPriceDiv(double s, double x, double q ,double r, double sigma, double t){
        double putPriceBlackDiv = 0;
        double d1 = (Math.log(s/x) + (r - q + (sigma * sigma)/2)*t)/ sigma * Math.sqrt(t);
        double d2 = d1 - sigma* Math.sqrt(t);
        putPriceBlackDiv = x*Math.exp(-r*t)* cumulativeNormal(-d2) - s * Math.exp(-q*t) *cumulativeNormal(-d1) ;
        return putPriceBlackDiv;
    }

    // European Currency Options

    public static double callPriceCurr(double s, double x, double rf ,double r, double sigma, double t){
        double callPriceBlackDiv = 0;
        double d1 = (Math.log(s/x) + (r - rf + (sigma * sigma)/2)*t)/ sigma * Math.sqrt(t);
        double d2 = d1 - sigma* Math.sqrt(t);
        callPriceBlackDiv = s * Math.exp(-rf*t) *cumulativeNormal(d1) - x*Math.exp(-r*t)* cumulativeNormal(d2);
        return callPriceBlackDiv;
    }

    public static double putPriceCurr(double s, double x, double rf ,double r, double sigma, double t){
        double putPriceBlackDiv = 0;
        double d1 = (Math.log(s/x) + (r - rf + (sigma * sigma)/2)*t)/ sigma * Math.sqrt(t);
        double d2 = d1 - sigma* Math.sqrt(t);
        putPriceBlackDiv = x*Math.exp(-r*t)* cumulativeNormal(-d2) - s * Math.exp(-rf*t) *cumulativeNormal(-d1) ;
        return putPriceBlackDiv;
    }



}
