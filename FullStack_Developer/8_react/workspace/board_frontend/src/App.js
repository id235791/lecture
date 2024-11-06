import {BrowserRouter, Route, Routes} from 'react-router-dom'
import './assets/style/style.css';
import Main from './pages/Main';
import Join from './pages/user/Join';
import MyPage from './pages/user/MyPage';
import List from './pages/board/List';
import Write from './pages/board/Write';
import Get from './pages/board/Get';
import Modify from './pages/board/Modify';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main></Main>}></Route>
        <Route path="/user/join" element={<Join></Join>}></Route>
        <Route path="/user/myInfo" element={<MyPage></MyPage>}></Route>
        <Route path="/board/list" element={<List></List>}></Route>
        <Route path="/board/write" element={<Write></Write>}></Route>
        <Route path="/board/:boardnum" element={<Get></Get>}></Route>
        <Route path="/board/modify" element={<Modify></Modify>}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App;
