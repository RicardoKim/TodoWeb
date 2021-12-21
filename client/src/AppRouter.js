import React, { Component } from 'react'
import "./index.css";
import App from "./App";
import Login from "./components/Login";
import { BrowserRouter, Route, Routes } from "react-router-dom";

  
const AppRouter = () => {
   
    return (
        <BrowserRouter>
            <Routes>
                <Route exact path = "/login" element = {<Login/>}/>
                <Route exact path = "/" element = {<App/>}/>
            </Routes>
        </BrowserRouter>
    
    );

}
  
  export default AppRouter;
