import React, {FC} from 'react';
import Carousel from "react-bootstrap/Carousel";
import {brandsItem1, brandsItem2, brandsItem3} from "./SliderBrandsData";
import "./SliderBrands.css";
import {Link} from "react-router-dom";
import {BrandType} from "../../types/types";

function brands(index: number, brandName: string, brandUrl: string) {
    return (<div className="col-2 float-left" key={index}>
        <Link to={{pathname: "/menu", state: {id: brandName}}}>
            <img id={brandName} className="img-fluid" src={brandUrl} alt={brandName}/>
        </Link>
    </div>)
}

const SliderBrands: FC = () => {
    const settings = {
        controls: false,
        indicators: true
    };

    return (
        <div className="container text-center my-3 mt-5">
            <h3>BRANDS</h3>
            <Carousel {...settings}>
                <Carousel.Item className="row">
                    {brandsItem1.map((brand: BrandType, index: number) => (brands(index, brand.name, brand.url)))}
                </Carousel.Item>
                <Carousel.Item className="row">
                    {brandsItem2.map((brand: BrandType, index: number) => (brands(index, brand.name, brand.url)))}
                </Carousel.Item>
                <Carousel.Item className="row">
                    {brandsItem3.map((brand: BrandType, index: number) => (brands(index, brand.name, brand.url)))}
                </Carousel.Item>
            </Carousel>
        </div>
    );
};

export default SliderBrands;
