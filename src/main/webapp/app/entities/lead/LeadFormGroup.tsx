import React from 'react';
import '../lead/LeadUpdate.css';
const LeadFormGroup = ({ children }) => {
    return (
        <div className="Form">  {/* Add your desired class names here */}
            {children}
        </div>
    );
};
export default LeadFormGroup;