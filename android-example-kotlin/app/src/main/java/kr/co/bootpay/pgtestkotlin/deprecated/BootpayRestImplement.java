package kr.co.bootpay.pgtestkotlin.deprecated;

//이  로직은 서버와 통신함에 있어서 참조용 코드일 뿐입니다.

@Deprecated
public interface BootpayRestImplement {
    @Deprecated
    void callbackRestToken(TokenData acessToken);

    @Deprecated
    void callbackEasyPayUserToken(EasyPayUserTokenData userToken);
}
