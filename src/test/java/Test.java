import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/6/22
 * @since v1.0
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(((Test2)createObject()).value);
    }

    public static Object createObject(){
        Object ret = null;
        String clazzName = "Test2";
        try{
            Class clazz = Class.forName(clazzName);
//            ret = clazz.newInstance();//针对无参的构造方法
            //如果知道是单例的类且确定取对象的方法可以直接用method来用
            Method method = clazz.getDeclaredMethod("getInstance");
            ret = method.invoke(null,null);
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

    int value ;

    private Test2(){
        value =1;
    }

    public static Test2 getInstance(){
        return new Test2();
    }
}