import React, { Component } from 'react'
import "./index.css";
import App from "./App";
import Login from "./components/Login";
import { BrowserRouter, Route, Routes } from "react-router-dom";

const AppRouter = () => {
   
    return (
        <BrowserRouter>
            <Routes>
                <Route path = "/login" element = {<Login/>}/>
                <Route path = "/" element = {<App/>}/>
            </Routes>
        </BrowserRouter>
    
    );

}
  
  export default AppRouter;
