import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Header from './components/Header'
import Footer from './components/Footer'
import Join from './components/Join';
import Info from './components/Info';
import BoardContainer from './container/BoardContainer'
import Product from './components/Product';

function App() {
  return (
    <BrowserRouter>
      <Header/>
      <Routes>
        <Route path="/join" element={<Join/>}></Route>
        <Route path="/info" element={<Info/>}></Route>
        <Route path="/board" element={<BoardContainer/>}></Route>
        <Route path="/product/:prodnum" element={<Product/>}></Route>
      </Routes>
      <Footer/>
    </BrowserRouter>
  );
}

export default App;
