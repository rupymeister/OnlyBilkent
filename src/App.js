import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';
import Layout from './component/Layouts'
import {Routes, Route} from 'react-router-dom';
import Home from  './component/home/Home'

function App() {

  const [users, setUsers] = useState();
  
  const getAllUsers = async () =>{

    try{
      console.log("1")
      const response = await api.get('/users');
      console.log(response.data);
      console.log("2")
      setUsers(response.data)

    }catch(err){
      console.log(err);
      console.log("burada")
    }

  }

  useEffect(() => {
    getAllUsers();
  },[])

  return (
    <div className = "App">

    <Routes>
      <Route path ="/" element={<Layout/>}>
          <Route path ="/" element={<Home/>}> </Route>
      </Route>
    </Routes>

    </div>
  );
}

export default App;
