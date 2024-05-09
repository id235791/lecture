import Button from './Button';

const Login = ({userid,userpw,change,clickLogin}) =>{
    return (
        <>
            <div id='wrap' className="login">
                <form name="loginForm" action="/user/login" method="post">
                    <table>
                        <tbody>
                            <tr>
                                <th>아이디</th>
                                <td>
                                    <input type="text" id="userid" name="userid" value={userid} onChange={change}/>
                                </td>
                            </tr>			
                            <tr>
                                <th>비밀번호</th>
                                <td>
                                    <input type="password" id="userpw" name="userpw" value={userpw} onChange={change}/>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="2">
                                    <div className="btn_area">
                                        {/* <a href="#" className="common-btn login" onClick={clickLogin}>로그인</a>  */}
                                        <Button text="로그인" size="common" className="login" onClick={clickLogin}/>
                                        <Button text="회원가입" size="common" className="join" href="/join"/>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>                
                </form>
            </div>
        </>
    )
}
export default Login;