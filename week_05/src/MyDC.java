import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyDC {
    /**
     * 将 后缀表达式 进行  运算 计算出结果
     * @return
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        MyBC string = new MyBC();
        MyDC calstring = new MyDC();
        System.out.println("请输入中缀表达式(输入end退出)");
        while (s.hasNextLine()) {
            String str = s.nextLine();
            String rstr = string.toSuffix(str);
            String result = calstring.dealEquation(rstr);
            if ("end".equals(str)) {
                System.out.println("谢谢使用!");
                break;
            }

            System.out.println(result);
        }
    }
    public String dealEquation(String equation){
        String [] arr = equation.split(",");                                    //根据, 拆分字符串
        List<String> list = new ArrayList<String>();                            //用于计算时  存储运算过程的集合【例如list中当前放置  100   20  5  /  则取出20/5 最终将结果4存入list   此时list中结果为  100  4 】


        for (int i = 0; i < arr.length; i++) {                                    //此处就是上面说的运算过程， 因为list.remove的缘故，所以取出最后一个数个最后两个数  都是size-2
            int size = list.size();
            switch (arr[i]) {
                case "+": double a = Double.parseDouble(list.remove(size-2))+ Double.parseDouble(list.remove(size-2)); list.add(String.valueOf(a));     break;
                case "-": double b = Double.parseDouble(list.remove(size-2))- Double.parseDouble(list.remove(size-2)); list.add(String.valueOf(b));     break;
                case "*": double c = Double.parseDouble(list.remove(size-2))* Double.parseDouble(list.remove(size-2)); list.add(String.valueOf(c));     break;
                case "/": double d = Double.parseDouble(list.remove(size-2))/ Double.parseDouble(list.remove(size-2)); list.add(String.valueOf(d));       break;
                default: list.add(arr[i]);     break;                                    //如果是数字  直接放进list中
            }
        }

        return list.size() == 1 ? list.get(0) : "运算失败" ;                    //最终list中仅有一个结果，否则就是算错了
    }
}
