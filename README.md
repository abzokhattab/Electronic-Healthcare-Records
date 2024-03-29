# Electronic Healthcare Records

## Objective 
The objective of the project is to create a blockchain application for Electronic Healthcare Records (EHR).

## Description
EHRs contain all information pertaining to a patient in the healthcare system. This includes general information about the patient (name, age, weight, height, sex, initial medical measurements such as blood pressure, pulse, oxygen, glucose, etc.), but also includes information about every visit to a healthcare professional. The information about every visit includes any readings that the patient takes in the clinic/hospital (blood pressure, glucose, temperature, pulse, etc.), the reason for the visit (periodic checkup, management of a case such as hypertension or diabetes, or patient is complaining about something), the doctor’s diagnosis, and the prescription. The prescription may include medications (with doses and intake periods), referrals to other doctors/specialists, follow-up appointments, and lab tests. Note that a lab test would be considered a separate visit to the healthcare system.

The goal of this project is to create a blockchain application for the EHR operations mentioned above. For each patient, an initial transaction is to be added to a block in the blockchain that contains the general patient information. Then, for each visit a patient makes in the system, another transaction will be added to the most recent block with the information of that visit. The transaction for each patient will be chained in a similar way to the transactions of a user on the Bitcoin blockchain. The following figure illustrates how the blockchain would look like after adding the EHR of one patient.

![](https://github.com/abzokhattab/Electronic-Healthcare-Records/raw/main/blockchain.png)

You may assume that the transactions are uploaded to the blockchain via a healthcare professional (doctor/nurse). Like transactions in any blockchain, they should have the security services of integrity and non-repudiation. This means that transactions that are uploaded cannot be modified (which is the case in any blockchain) and that transactions are digitally signed by the person who uploads them.


In addition, since we are dealing with EHRs, there is an additional requirement of confidentiality (normally not found in blockchain applications), since patient records contain sensitive information that cannot be viewed except by the authorized personnel. Thus you will have to figure out the proper set of cryptographic tools to achieve that. This means that we should be able to verify the transaction (the digital signature, validity of contents, chain to previous transactions), without reading the content of the transaction itself. There is a cryptocurrency known as ZCash that does something similar to that. You are advised to look into it. Please note that authorized personnel should still be able to read the EHRs. 
