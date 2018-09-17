import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/6/22
 * @since v1.0
 */
public class Test {

    public static void main(String[] args) {

        String regex = "((\\S*)国)?((((\\S*)省)|((\\S*)自治区)|((\\S*)直辖市)|(\\S)*?市)?(\\S*)市$)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("湖南省国家市");
        if (matcher.matches()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(i + "：" + matcher.group(i));
            }
        }

//        System.out.println(((Test2)createObject()).value);
    }

    public static Object createObject() {
        Object ret = null;
        String clazzName = "Test2";
        try {
            Class clazz = Class.forName(clazzName);
//            ret = clazz.newInstance();//针对无参的构造方法
            //如果知道是单例的类且确定取对象的方法可以直接用method来用
            Method method = clazz.getDeclaredMethod("getInstance");
            ret = method.invoke(null, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ret;
    }
}

class Test2 {

    int value;

    private Test2() {
        value = 1;
    }

    public static Test2 getInstance() {
        return new Test2();
    }
}