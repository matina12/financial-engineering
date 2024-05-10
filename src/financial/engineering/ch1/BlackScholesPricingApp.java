package financial.engineering.ch1;

import java.util.Random;

public class BlackScholesPricingApp {

    Random in = new Random();

    public static void main(String[] args) {

        double firstCallPrice=callPrice(28, 40, 0.06, Math.sqrt(0.5), 0.5);
        System.out.println("The call price of this option is: " + firstCallPrice);

    }

    public static double cumulativeNormal(double x)
    {
        int neg = (x < 0d) ? 1 : 0;
        if ( neg == 1)
            x *= -1d;

        double k = (1d / ( 1d + 0.2316419 * x));
        double y = (((( 1.330274429 * k - 1.821255978) * k + 1.781477937) *
                k - 0.356563782) * k + 0.319381530) * k;
        y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

        return (1d - neg) * y + neg * (1d - y);
    }




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
}
