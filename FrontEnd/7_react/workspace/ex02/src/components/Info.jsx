// const Info = (props) => {
//     return (
//         <>
//             <p>
//                 아이디 : {props.id}
//             </p>
//             <p>
//                 비밀번호 : {props.pw}
//             </p>
//             <p>
//                 이름 : {props.name}
//             </p>
//             <p>
//                 성별 : {props.man?"남":"여"}
//             </p>
//         </>
//     )
// }
const Info = ({id,pw,name,man}) => {
    return (
        <>
            <p>
                아이디 : {id}
            </p>
            <p>
                비밀번호 : {pw}
            </p>
            <p>
                이름 : {name}
            </p>
            <p>
                성별 : {man?"남":"여"}
            </p>
        </>
    )
}
Info.defaultProps = {
   id:"디폴트아이디",
   pw:"디폴트패스워드",
   name:"디폴트네임"
}

export default Info;