import System.IO

cmdEval :: String -> String
cmdEval = id

cmdPrint :: String -> IO ()
cmdPrint = putStrLn

cmdRead :: IO String
cmdRead = do
    putStr "user> "
    hFlush stdout
    inp <- getLine
    return inp

rep :: IO ()
rep = do
    inp <- cmdRead
    cmdPrint $ cmdEval inp
    rep

main :: IO ()
main = rep
