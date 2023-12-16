import React, {FC} from 'react';
import {faBusinessTime, faInbox, faInfoCircle, faTruck} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const Contacts: FC = () => {
    return (
        <div className="container mt-5">
            <h4><FontAwesomeIcon className="ml-2 mr-2" icon={faInfoCircle}/>Contacts</h4>
            <br/>
            <p>
                <b><FontAwesomeIcon className="ml-2 mr-2" icon={faInbox}/>E-mail:</b>
                <a id="email" href="mailto:qa.info@sngular.com?Subject=QA%20Sngular%20questions"> qa.info@sngular.com</a>
            </p>
            <br/>
            <h4><FontAwesomeIcon className="ml-2 mr-2" icon={faBusinessTime}/>Working time</h4>
            <p>The online store is open from 08:00 to 20:00 without breaks and weekends. <br/>
                Online orders are accepted around the clock.</p>
            <br/>
            <h4><FontAwesomeIcon className="ml-2 mr-2" icon={faTruck}/>Delivery</h4>
            <p>Delivery of orders come through courier service.</p>
        </div>
    );
};

export default Contacts
