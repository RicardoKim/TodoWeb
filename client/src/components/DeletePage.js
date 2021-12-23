import { Typography, Grid,Button, TextField } from '@material-ui/core';
import {React, useState} from 'react';
import "../css/DeletePage.css"
import deleteImage from "../img/DeleteImage.png"
import { deleteAccount } from '../service/ApiService';

export default function DeletePopup() {
    const [password, setPassword] = useState([]);
    const [IsDeleteSucceed, setIsDeleteSucceed] = useState([true]);
    const onInputChange = (e) => {
        setPassword(e.target.value);
        console.log(password)
    }
    const deleteButton = (e) => {
        deleteAccount(password).then((result) =>{
            setPassword("잘못 입력하셨습니다");
        }) 
    };
    const cancel = () =>{
        window.location.href = "/";
    }
    return (
        <div className='DeletePoup'>
            <div className = "Image">
                <img src = {deleteImage}/>
            </div>
            <div className = "Notice">
                 
                <Grid>
                    <Grid xs = {11} md = {11} item style ={{paddingLeft :120}}>
                        <Typography variant = "h5">탈퇴 하시겠습니까?</Typography>
                    </Grid>
                </Grid>
            </div>
            <div className = "Notice">
                <Grid>
                    <Grid xs = {11} md = {11} item style ={{paddingLeft :90}}>
                        <Typography variant = "h6">사용자 패스워드를 입력하세요</Typography>
                    </Grid>
                </Grid>
            </div>
            <div className = "PasswordArea">
                <TextField 
                    placeholder="패스워드를 입력하세요" 
                    fullWidth
                    value = {password}
                    onChange = {onInputChange}
                  
                />
            </div>
            <div className='ButtonArea'>
                <Grid justify="space-between" container>
                    <Button variant='contained' color="primary" onClick={deleteButton}>
                        탈퇴
                    </Button>
                    <Button variant='contained'color = "inherit" onClick={cancel}>
                        취소
                    </Button>
                </Grid>
            </div >
            <div className='ButtonArea'>
                
            </div>
            
        </div>
        
    )
}
