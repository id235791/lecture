import { useLocation, useSearchParams } from "react-router-dom";

const Info = () => {
    //url을 실제로 이동시키진 않고 컨트롤만
    const location = useLocation();
    console.log(location.pathname)
    const man = location.hash;
    console.log(man)
    
    //query string을 위한 searchParams
    // const [searchParams,setSearchParams] = useSearchParams();
    const searchParams = new URLSearchParams(location.search);
    const id = searchParams.get("id");
    const pw = searchParams.get("pw");
    const name = searchParams.get("name");
    
    return (
        <>
            <p>
                아이디 : {id||"디폴트아이디"}
            </p>
            <p>
                비밀번호 : {pw||"디폴트패스워드"}
            </p>
            <p>
                이름 : {name||"디폴트네임"}
            </p>
            <p>
                성별 : {man?"남":"여"}
            </p>
        </>
    )
}
// Info.defaultProps = {
//    id:"디폴트아이디",
//    pw:"디폴트패스워드",
//    name:"디폴트네임"
// }

export default Info;