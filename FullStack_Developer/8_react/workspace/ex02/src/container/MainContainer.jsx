import Footer from "../components/Footer"
import Header from "../components/Header"
import Info from "../components/Info"
import Join from "../components/Join"
import BoardContainer from "./BoardContainer"

const MainContainer = () => {
    const command = document.location.href.split("/").pop();
    
    if(command == "info"){
        const user = {id:"apple", pw:"1234", name:"김사과"}
        return(
            <>
                <Header></Header>
                <Info
                    id={user.id}
                    pw={user.pw}
                    name={user.name}
                    man
                />
                <Footer></Footer>
            </>
        )
    }
    else if(command == "join"){
        return(
            <>
                <Header></Header>
                <Join></Join>
                <Footer></Footer>
            </>
        )
    }
    else if(command == "board"){
        return(
            <>
                <Header></Header>
                <BoardContainer></BoardContainer>
                <Footer></Footer>
            </>
        )
    }
    else{
        return (
            <>오류페이지</>
        )
    }
}
export default MainContainer;