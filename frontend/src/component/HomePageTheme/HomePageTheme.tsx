import React, {FC} from 'react';
import {Link} from "react-router-dom";

const HomePageTheme: FC = () => {
    return (
        <div className="container mt-5">
            <div className="row">
                <div className="col-lg-6">
                    <div className="card mb-5" >
                        <Link to={{pathname: "/menu", state: { id: "female" }}}>
                            <img id="womens_perfume" className="img-fluid" src="https://i.ibb.co/jMmJs60/Them-Woman-ENG.jpg" alt="women's perfume"/>
                        </Link>
                    </div>
                </div>
                <div className="col-lg-6">
                    <div className="card mb-5">
                        <Link to={{pathname: "/menu", state: { id: "male" }}}>
                            <img id="mens_perfume" className="img-fluid" src="https://i.ibb.co/mJGKz8c/Them-Man-ENG.jpg" alt="men's perfume"/>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HomePageTheme;
