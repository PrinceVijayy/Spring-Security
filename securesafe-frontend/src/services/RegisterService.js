import axios from "axios";
const baseUrl="http://localhost:8080/securesafe/register"
class RegisterService{

    register(credentials){
        return axios.post(`${baseUrl}`,credentials)
    }

}
export default new RegisterService();