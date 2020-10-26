using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameMgr : MonoBehaviour
{
    public static bool created = false;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

   void Awake()
    {
        if (!created)
        {
            DontDestroyOnLoad(this.gameObject);
            created = true;
            Debug.Log("Awake: " + this.gameObject);
        }
    }

    public void LoadScene()
    {
        SceneManager.LoadScene("Lab03z02", LoadSceneMode.Single);
    }
    public void Exit()
    {
        Application.Quit();
        Debug.Log("Game is exiting");
    }

    public void ChangeScene()
    {

    }
}
