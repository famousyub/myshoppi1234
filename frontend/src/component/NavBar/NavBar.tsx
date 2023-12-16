import React, {FC} from 'react';
import {Link} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSignInAlt, faSignOutAlt, faUser, faUserPlus} from "@fortawesome/free-solid-svg-icons";

import {logout} from "../../redux/thunks/auth-thunks";
import "./NavBar.css";
import {AppStateType} from "../../redux/reducers/root-reducer";
import {Perfume} from "../../types/types";
import logo from "../../img/logo.png";

const NavBar: FC = () => {
    const dispatch = useDispatch();
    const perfumes: Array<Perfume> = useSelector((state: AppStateType) => state.cart.perfumes);
    const isLoggedIn: boolean = useSelector((state: AppStateType) => state.user.isLoggedIn);

    const handleLogout = () => {
        dispatch(logout())
    };

    let links;
    let signOut;

    if (localStorage.getItem("isLoggedIn") || isLoggedIn) {
        links = (
            <li className="nav-item">
                <Link to={"/account"}><span id="my_account" className="nav-link pl-5 pr-5">
                         <FontAwesomeIcon className="mr-2" icon={faUser}/>MY ACCOUNT</span></Link>
            </li>
        );
        signOut = (
            <Link id="exit" to={"/"} onClick={handleLogout}>
                <FontAwesomeIcon className="mr-2" icon={faSignOutAlt}/>EXIT
            </Link>
        );
    } else {
        links = (
            <>
                <li className="nav-item">
                    <Link id="sign_in" to={"/login"} className="nav-link pl-5 pr-3">
                        <FontAwesomeIcon className="mr-2" icon={faSignInAlt}/>SIGN IN</Link>
                </li>
                <li className="nav-item">
                    <Link id="sign_up" to={"/registration"} className="nav-link">
                        <FontAwesomeIcon className="mr-2" icon={faUserPlus}/>SIGN UP</Link>
                </li>
            </>
        );
        signOut = null;
    }

    return (
        <div>
            <div id="header" className="container-fluid header-top d-none d-md-block pb-5 pt-5">
                <img src={logo} className="rounded mx-auto d-block" alt="perfume webstore"/>
            </div>
            <div className="container-fluid bg-black">
                <nav id="navbar-main" className={`container navbar navbar-expand-lg bg-black text-white `}
                     style={{fontSize: "18px"}}>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mr-auto ">
                            <li className="nav-item">
                                <Link to={"/"}><span id="home" className="nav-link pl-5 pr-5">HOME</span></Link>
                            </li>
                            <li className="nav-item">
                                <Link to={{pathname: "/menu", state: {id: "all"}}}>
                                    <span id="perfumes" className="nav-link pl-5 pr-5">PERFUMES</span></Link>
                            </li>
                            <li className="nav-item">
                                <Link to={"/contacts"}><span id="contacts" className="nav-link pl-5 pr-5">CONTACTS</span></Link>
                            </li>
                        </ul>
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link id="cart" className="nav-link" to={"/cart"}>
                                    <i className="fas fa-shopping-cart fa-lg pl-5" style={{color: "white"}}></i>
                                    <h5 className="d-inline"
                                        style={{position: "relative", right: "15px", bottom: "8px"}}>
                                        <span className="badge badge-success">{perfumes.length}</span>
                                    </h5>
                                </Link>
                            </li>
                            {links}
                        </ul>
                        {signOut}
                    </div>
                </nav>
            </div>
        </div>
    );
};

export default NavBar;
