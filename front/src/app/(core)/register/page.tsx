import { NextPage } from "next";
import AddUser from "./components/add.user";
import Logo from "@/components/icons/logo";

const Register: NextPage = () => {
  return (
    <section className="min-h-screen flex items-center justify-center">
      <div className="sm:bg-zinc-50 w-full sm:w-[400px] p-6 sm:shadow flex flex-col gap-5 rounded ">
        {/* <div className="flex flex-col items-center justify-between">
          <Logo size="xl" />
          <small className="text-sm text-zinc-500 font-semibold">
            Registro de usuario
          </small>
        </div> */}
        <AddUser />
      </div>
    </section>
  );
};

export default Register;
