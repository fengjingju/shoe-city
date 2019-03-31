package shoecity.shoecity.exception;

/**
 * @author FENGJINGJU
 * @date 2018/9/22 23:19
 */
public class ParamRuleException extends Exception {

    //无参构造方法
    public ParamRuleException(){
        super();
    }

    //有参的构造方法
    public ParamRuleException(String message){
        super(message);
    }
}
