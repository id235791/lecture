import {BrowserRouter, Route, Routes } from 'react-router-dom';
import Main from './pages/home/Main';
import Join from './pages/user/Join';
import List from './pages/board/List';
import './assets/css/style.css';
import Write from './pages/board/Write';
import Get from './pages/board/Get';
import Modify from './pages/board/Modify';
import MyPage from './pages/user/MyPage';

function App() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main></Main>}></Route>
                <Route path="/user/join" element={<Join></Join>}></Route>
                <Route path="/board/list" element={<List></List>}></Route>
                <Route path="/board/write" element={<Write></Write>}></Route>
                <Route path="/board/:boardnum" element={<Get></Get>}></Route>
                <Route path="/board/modify/:boardnum" element={<Modify></Modify>}></Route>
                <Route path="/user/myInfo" element={<MyPage></MyPage>}></Route>
            </Routes>
        </BrowserRouter>
    )
}
export default App;