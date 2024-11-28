import { type ReactNode } from "react";

export const ErrorMessage = ({
    errorMessageId,
    children
}: {
    errorMessageId: string;
    children?: ReactNode;
}) => {
    return (
        <p id={errorMessageId} className="text-red-600 text-sm mt-1 font-medium">
            {children}
        </p>
    );
};
