
public class Main  {
    public static void main(String[] args) {
        Stock tenk = new Stock(1,"10k E",20.20,10,"PTH");
        Stock twentyk = new Stock(2,"20k E",20.20,100,"SMD");
        Stock thirthyk = new Stock(3,"30k E",20.20,1000,"PTH");
        
        StockService stockService = new StockService();

       stockService.addStock(tenk,twentyk);
       stockService.addStock(thirthyk);

       stockService.getStocks();
        System.out.println();

        System.out.println(stockService.searchStock(3));
        System.out.println(stockService.searchStock("20k E"));

        System.out.println(stockService.deleteStock(1));

        stockService.getStocks();

        thirthyk.setStock_quantity(100000);

        stockService.getStocks();

    }
}