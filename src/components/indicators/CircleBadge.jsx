
function CircleBadge({children, size=24, className="", bgColor="", textColor=""}) {

    return (
        <div className={`flex items-center justify-center rounded-full ${bgColor} ${textColor}`} style={{width: size, height: size, minWidth: size, minHeight: size}}>
            {children}
        </div>
    )
}

export default CircleBadge;