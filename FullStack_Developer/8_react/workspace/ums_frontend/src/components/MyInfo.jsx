import { useEffect } from "react";
import Button from "./Button";
import Postcode from "./DaumPostCode";

const MyInfo = ({user,clickUpdate,pwCheck,hobbyKeyUp,addHobby,deleteHobby,userhobby,clickLeave}) => {
    useEffect(()=>{
        const hobby_list = document.getElementsByClassName("hobby_list")[0];
        hobby_list.innerHTML = "";
        pwCheck();
        const temp = user.userhobby.split("\\");
        for(let item of temp){
            const inputHobby = document.createElement("span");
            inputHobby.classList = "userhobby";
            inputHobby.name = "userhobby";
            inputHobby.innerHTML = "<span>"+item+"</span>";
            userhobby.push(item);
            
            const xBox = document.createElement("a")
            xBox.classList = "xBox";
            xBox.addEventListener('click',function(e){
                let deleteHobby = e.target.parentElement.innerText;
                for(let i in userhobby){
                    if(userhobby[i] == deleteHobby){
                        userhobby.splice(i,1);
                        break;
                    }
                }
                e.target.parentElement.remove();
            })
            inputHobby.appendChild(xBox);
            
            console.log(hobby_list);
            hobby_list.appendChild(inputHobby);
            
            const hobbies = document.querySelectorAll(".userhobby>span");
            for(let i=0;i<hobbies.length;i++){
                hobbies[i].addEventListener('click',deleteHobby)
            }
            
            document.myinfoForm.hobby.value="";
        }
    })

    return (<>
        <div id='wrap' className='myinfo'>
            <form name="myinfoForm" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td id="result" colSpan={2}></td>
                        </tr>
                        <tr className="userid_area">
                            <th>아이디</th>
                            <td>
                                <input type="text" name="userid" defaultValue={user.userid} readOnly/>
                            </td>
                        </tr>
                        <tr className="userpw_area">
                            <th>비밀번호</th>
                            <td>
                                <input type="password" name="userpw" onChange={pwCheck} defaultValue={user.userpw}/>
                            </td>
                        </tr>
                        <tr className="userpw_re_area" >
                            <th>비밀번호 확인</th>
                            <td>
                                <input type="password" name="userpw_re" onChange={pwCheck} defaultValue={user.userpw}/>
                            </td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <div className="pw_check">
                                    <span>영어 대문자, 소문자, 숫자, 특수문자(~,?,!,@,-)를 모두 하나 이상 포함해야 해요 😃</span>
                                    <span>최소 8자 이상의 비밀번호가 보안에 안전해요 😄</span>
                                    <span>같은 문자가 연속해서 사용되지 않았어요 😆</span>
                                    <span>사용할 수 없는 문자가 포함되지 않았어요 🙂</span>
                                    <span>비밀번호 확인이 완료되었어요! 😊</span>
                                </div>                                
                            </td>
                        </tr>
                        <tr className="username_area">
                            <th>이름</th>
                            <td>
                                <input type="text" name="username" defaultValue={user.username} readOnly/>
                            </td>
                        </tr>
                        <tr className="gender_area radio_area">
                            <th>성별</th>
                            <td>
                                <div>
                                    <ul>
                                        <li className="radio_item">
                                            <input type="radio" name="usergender" id="usergender1" value='M' defaultChecked={user.usergender[0]=='M'} disabled/>
                                            <label htmlFor="usergender1">남자</label>
                                        </li>
                                        <li className="radio_item">
                                            <input type="radio" name="usergender" id="usergender2" value='W' defaultChecked={user.usergender[0]=='W'} disabled/>
                                            <label htmlFor="usergender2">여자</label>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li className="radio_item">
                                            <input type="radio" name="foreigner" id="foreigner1" value='K' defaultChecked={user.usergender[2]=='K'} disabled/>
                                            <label htmlFor="foreigner1">내국인</label>
                                        </li>
                                        <li className="radio_item">
                                            <input type="radio" name="foreigner" id="foreigner2" value='F' defaultChecked={user.usergender[2]=='F'} disabled/>
                                            <label htmlFor="foreigner2">외국인</label>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <tr className="zipcode_area">
                            <th>우편번호</th>
                            <td>
                                <input className="input-with-btn" name="zipcode" type="text" id="zipcode" placeholder="우편번호" readOnly onClick={()=>{document.getElementById("post_btn").click()}} defaultValue={user.zipcode}/>
                                <Postcode></Postcode>
                            </td>
                        </tr>
                        <tr className="addr_area">
                            <th>주소</th>
                            <td>
                                <input readOnly name="addr" type="text" id="addr" placeholder="주소" onClick={()=>{document.getElementById("post_btn").click()}} defaultValue={user.addr}/>
                            </td>
                        </tr>
                        <tr className="addrdetail_area">
                            <th>상세주소</th>
                            <td>
                                <input name="addrdetail" type="text" id="addrdetail" placeholder="상세주소" defaultValue={user.addrdetail}/>
                            </td>
                        </tr>
                        <tr className="addretc_area">
                            <th>참고항목</th>
                            <td>
                                <input readOnly name="addretc" type="text" id="addretc" placeholder="참고항목" onClick={()=>{document.getElementById("post_btn").click()}} defaultValue={user.addretc}/>
                            </td>
                        </tr>
                        <tr className="hobby_area">
                            <th id="hobby_lbl">취미</th>
                            <td>
                                <div>
                                    <div className="hobby_input">
                                        <input className="input-with-btn" type="text" name="hobby" onKeyUp={hobbyKeyUp}/><Button onClick={addHobby} text="추가" size="small"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <div className="hobby_list"></div>
                                <input type="hidden" value="" name="userhobby"/>
                            </td>
                        </tr>
                        
                        <tr>
                            <td colSpan={2}>
                                <Button className='submit' text="수정 완료" size="small" onClick={clickUpdate}/>
                                <Button className='back' text="메인으로" size="small" onClick={(e)=>{e.preventDefault();document.location.replace("/list");}}/>
                                <Button className='leave' text="회원탈퇴" size="small" onClick={clickLeave}/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </>)
}
export default MyInfo;