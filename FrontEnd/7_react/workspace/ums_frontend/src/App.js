import {BrowserRouter, Route, Routes} from 'react-router-dom'
import LoginContainer from './container/LoginContainer';
import JoinContainer from './container/JoinContainer';
import ListContainer from './container/ListContainer';
import AddContainer from './container/AddContainer';
import GetContainer from './container/GetContainer';
import ModifyContainer from './container/ModifyContainer';
import MyInfoContainer from './container/MyInfoContainer';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<LoginContainer/>}/>
        <Route path='/join' element={<JoinContainer/>}/>
        <Route path='/list' element={<ListContainer/>}/>
        <Route path='/addProduct' element={<AddContainer/>}/>
        <Route path='/get/:prodnum' element={<GetContainer/>}/>
        <Route path='/modify/:prodnum' element={<ModifyContainer/>}/>
        <Route path='/myInfo' element={<MyInfoContainer/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
