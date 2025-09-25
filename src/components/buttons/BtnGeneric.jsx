import { Icon } from "@iconify/react";

function BtnGeneric({
  children,
  onClick,
  icon,
  color,
  size,
  disabled = false,
  className = "",
  type = "button",
}) {
  const colors = {
    save: "bg-green-500 hover:bg-green-600 text-white",
    delete: "bg-red-500 hover:bg-red-600 text-white",
    update: "bg-blue-600 hover:bg-blue-700 text-white",
    search: "bg-gray-400 hover:bg-gray-500 text-white"
  };

  const sizes = {
    sm: "px-3 py-1 text-sm",
    md: "px-6 py-2 text-base",
    lg: "px-8 py-3 text-lg",
  };

  return (
    <button
      type={type}
      onClick={onClick}
      disabled={disabled}
      className={`rounded cursor-pointer disabled:bg-gray-400 disabled:cursor-not-allowed transition flex items-center gap-2 ${colors[color]} ${sizes[size]} ${className}`}>

        {icon && <Icon icon=""/>}

        {children}
    </button>
  );
}

export default BtnGeneric;
