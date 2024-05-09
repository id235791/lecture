import Button from '../Button';

const Header = ({loginUser}) => {
    return (
        <header>
            <table className="title">
                <tbody>
                    <tr>
                        <td>
                            <a href="/list">
                                <h3>UMS 프로젝트</h3>
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table className="header_area">
                <tbody>
                    <tr>
                        <td>
                            <span>{loginUser}님 환영합니다</span>
                        </td>
                        <td>
                            <Button href="/user/logout" text={"로그아웃"} size={"half"} id={"logout-btn"}></Button>
                            <Button href="/myInfo" text={"마이페이지"} size={"half"} id={"mypage-btn"}></Button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </header>
    )
}
export default Header;